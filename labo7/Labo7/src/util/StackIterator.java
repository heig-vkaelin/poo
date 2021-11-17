package util;

public class StackIterator<T> {
    private Element<T> element;
    
    public StackIterator(Element<T> element) {
        this.element = element;
    }
    
    public boolean hasNext() {
        return element.getNext() != null;
    }
    
    public Element<T> next() {
        element = element.getNext();
        return element;
    }
}
