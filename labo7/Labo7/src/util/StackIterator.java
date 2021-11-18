package util;

public class StackIterator<T> {
    private Element<T> element;
    
    public StackIterator(Element<T> element) {
        this.element = element;
    }
    
    public boolean hasNext() {
        return element != null;
    }
    
    public Element<T> next() {
        Element<T> current = element;
        element = element.getNext();
        return current;
    }
}
