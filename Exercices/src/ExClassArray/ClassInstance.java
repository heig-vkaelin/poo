package ExClassArray;

public class ClassInstance {
    private int counter = 1;
    private final Class classType;
    
    public ClassInstance(Class classType) {
        this.classType = classType;
    }
    
    public int getCounter() {
        return counter;
    }
    
    public Class getClassType() {
        return classType;
    }
    
    public void increment() {
        ++counter;
    }
    
    @Override
    public String toString() {
        return classType.getSimpleName() + " : " + counter;
    }
}
