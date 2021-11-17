package util;

public class Element<T> {
    private T value;
    private Element<T> next;
    
    public Element(T value, Element<T> next) {
        this.value = value;
        this.next = next;
    }
    
    public Element(T value) {
        this(value, null);
    }
    
    public T getValue() {
        return value;
    }
    
    public void setValue(T value) {
        this.value = value;
    }
    
    public Element<T> getNext() {
        return next;
    }
    
    public void setNext(Element<T> next) {
        this.next = next;
    }
}
