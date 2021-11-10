package ExClassArray;

public class ClassArray {
    private int size = 0;
    private final ClassInstance[] classes;

    public ClassArray(int maxSize) {
        classes = new ClassInstance[maxSize];
    }

    private void push(Object o) {
        for (int i = 0; i < size; ++i) {
            if (classes[i].getClassType() == o.getClass()) {
                classes[i].increment();
                return;
            }
        }
        classes[size++] = new ClassInstance(o.getClass());
    }

    public void printTypes(Object[] objects) {
        for (Object object : objects) {
            push(object);
        }
        System.out.println(this);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < size; ++i) {
            str.append(classes[i]).append("\n");
        }
        return str.toString();
    }
}
