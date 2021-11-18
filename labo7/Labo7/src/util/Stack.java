package util;

public class Stack {
    private Element top;
    private int size;
    
    /**
     * On initialise explicitement les attributs pour plus de clarté
     */
    public Stack() {
        top = null;
        size = 0;
    }
    
    /**
     * @param value
     */
    public void push(Object value) {
        Element element = new Element(value);
        element.setNext(this.top);
        top = element;
        ++size;
    }
    
    /**
     * @return
     * @throws RuntimeException
     */
    public Object pop() throws RuntimeException {
        if (top == null)
            throw new RuntimeException(
                    "Impossible de récupérer un élément d'une pile vide.");
        
        Object value = top.getValue();
        top = top.getNext();
        --size;
        return value;
    }
    
    /**
     * @return
     */
    public Object[] state() {
        Object[] result = new Object[size];
        
        StackIterator i = iterator();
        int index = 0;
        while (i.hasNext()) {
            result[index++] = i.next().getValue();
        }
        return result;
    }
    
    /**
     * @return
     */
    public StackIterator iterator() {
        return new StackIterator(top);
    }
    
    /**
     * @return
     */
    @Override
    public String toString() {
        StackIterator i = iterator();
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        while (i.hasNext()) {
            sb.append(" <");
            sb.append(i.next().getValue());
            sb.append("> ");
        }
        sb.append("]");
        return sb.toString();
    }
    
}
