package util;

public class StackIterator {
    private Element element;
    
    public StackIterator(Element element) {
        this.element = element;
    }
    
    public boolean hasNext() {
        return element != null;
    }
    
    public Element next() {
        Element current = element;
        element = element.getNext();
        return current;
    }
}
