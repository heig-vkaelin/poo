package util;

public class StackIterator {
    private Element element;
    
    /**
     * @param element
     */
    public StackIterator(Element element) {
        this.element = element;
    }
    
    /**
     * @return
     */
    public boolean hasNext() {
        return element != null;
    }
    
    /**
     * @return
     */
    public Element next() {
        if (!hasNext())
            throw new RuntimeException("Il n'y a pas d'élément suivant!");
        
        Element current = element;
        element = element.next;
        return current;
    }
}
