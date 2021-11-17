package util;

public class Stack<T> {
    private Element<T> top;
    private int size;
    
    /**
     * On initialise explicitement les attributs pour plus de clarté
     */
    public Stack() {
        top = null;
        size = 0;
    }
    
    public void push(T value) {
        Element<T> element = new Element<T>(value);
        element.setNext(this.top);
        top = element;
        ++size;
    }
    
    public T pop() throws RuntimeException {
        if (top == null)
            throw new RuntimeException(
                    "Impossible de récupérer un élément d'une pile vide.");
        
        T value = top.getValue();
        top = top.getNext();
        --size;
        return value;
    }
    
    public T[] state() {
        return null;
    }
    
    public StackIterator<T> iterator() {
        return new StackIterator<T>(new Element<T>(null, top));
    }
    
    @Override
    public String toString() {
        StackIterator<T> i = iterator();
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        while (i.hasNext()) {
            Element<T> e = i.next();
            sb.append(" <");
            sb.append(e.getValue());
            sb.append("> ");
        }
        sb.append("]");
        return sb.toString();
    }
    
}
