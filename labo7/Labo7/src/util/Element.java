package util;

public class Element {
    private final Object value;
    private Element next;
    
    public Element(Object value, Element next) {
        this.value = value;
        this.next = next;
    }
    
    public Element(Object value) {
        this(value, null);
    }
    
    public Object getValue() {
        return value;
    }
    
    public Element getNext() {
        return next;
    }
    
    public void setNext(Element next) {
        this.next = next;
    }
}
