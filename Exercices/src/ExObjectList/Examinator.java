package ExObjectList;

public class Examinator {
    private Element current;
    
    public Examinator(Element e) {
        current = e;
    }
    
    public boolean hasNext() {
        return current.getNext() != null;
    }
    
    public Object next() {
        current = current.getNext();
        return current;
    }
}
