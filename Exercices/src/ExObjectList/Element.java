package ExObjectList;

public class Element {
    private Object data;
    private Element next;
    
    Element(Object o, Element n) {
        data = o;
        next = n;
    }
    
    Element(Object o) {
        this(o, null);
    }
    
    public Object getData() {
        return data;
    }
    
    public Element getNext() {
        return next;
    }
    
    public void setNext(Element e) {
        next = e;
    }
}
