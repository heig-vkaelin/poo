package util;

public class Stack<T> {
    private Element<T> top;
    private int size;
    private T[] test;
    
    /**
     * On initialise explicitement les attributs pour plus de clarté
     */
    public Stack() {
        top = null;
        size = 0;
    }
    
    /**
     * @param value
     */
    public void push(T value) {
        Element<T> element = new Element<T>(value);
        element.setNext(this.top);
        top = element;
        ++size;
    }
    
    /**
     * @return
     * @throws RuntimeException
     */
    public T pop() throws RuntimeException {
        if (top == null)
            throw new RuntimeException(
                    "Impossible de récupérer un élément d'une pile vide.");
        
        T value = top.getValue();
        top = top.getNext();
        --size;
        return value;
    }
    
    /**
     * @return
     */
    @SuppressWarnings({"unchecked"})
    public T[] state() {
        Object[] result = new Object[size];
        
        StackIterator<T> i = iterator();
        int index = 0;
        while (i.hasNext()) {
            result[index++] = i.next().getValue();
        }
        return (T[]) result;
    }
    
    /**
     * @return
     */
    public StackIterator<T> iterator() {
        return new StackIterator<>(top);
    }
    
    /**
     * @return
     */
    @Override
    public String toString() {
        StackIterator<T> i = iterator();
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        while (i.hasNext()) {
            sb.append(" <");
            sb.append(i.next().getValue());
            sb.append("> ");
        }
        sb.append("]");
        return sb.toString();
    }
    
}
