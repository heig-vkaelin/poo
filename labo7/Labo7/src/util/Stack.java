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
        top = new Element(value, top);
        ++size;
    }
    
    /**
     * @return
     * @throws RuntimeException
     */
    public Object pop() {
        if (top == null)
            throw new RuntimeException(
                    "Impossible de récupérer un élément d'une pile vide.");
        
        Object value = top.value;
        top = top.next;
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
            result[index++] = i.next().value;
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
            sb.append(i.next().value);
            sb.append("> ");
        }
        sb.append("]");
        return sb.toString();
    }
    
}
