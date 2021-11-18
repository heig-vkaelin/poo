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
        if (!hasNext())
            throw new RuntimeException("Il n'y a pas d'élément suivant!");
        
        Element current = element;
        element = element.next;
        return current;
    }
}
