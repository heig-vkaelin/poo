package ExObjectList;

public class Examinator {
    private Element current;
    
    public Examinator(Element e) {
        current = e;
    }
    
    public boolean hasNext() {
        return current.getNext() != null;
    }
    
    // Il veut return Object et pas Element ici
    public Object next() {
        return current.getNext();
    }
}
