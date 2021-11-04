package ExObjectList;

public class ObjectList {
    private Element head;
    private int size;
    
    ObjectList(Object o) {
        head = new Element(o);
        size = 1;
    }
    
    public boolean isEmpty() {
        return head == null;
    }
    
    public int size() {
        /*
        int size = 0;
        Examinator i = examinator();
        while (i.hasNext()) {
            i.next();
            size++;
        }
        */
        return size;
    }
    
    public void insert(Object o) {
        ++size;
        head = new Element(o, head);
    }
    
    public void append(Object o) {
        ++size;
        Examinator ex = examinator();
        Element last = head;
        while (ex.hasNext()) {
            last = (Element) ex.next();
        }
        last.setNext(new Element(o));
    }
    
    public void remove(Object o) {
        --size;
        Examinator ex = examinator();
        Element e = head;
        while (ex.hasNext() && !e.getData().equals(o)) {
            e = (Element) ex.next();
        }
        e.setNext(e.getNext().getNext());
    }
    
    public Object get(int index) throws RuntimeException {
        if (index >= size) {
            throw new RuntimeException("Index invalide.");
        }
        int i = 0;
        Examinator ex = examinator();
        Element o = null;
        while (i != index) {
            o = (Element) ex.next();
            i++;
        }
        return o;
    }
    
    @Override
    public String toString() {
        Examinator i = examinator();
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Element e = head;
        sb.append(e.getData());
        sb.append(" ");
        while (i.hasNext()) {
            Element o = (Element) i.next();
            sb.append(o);
            sb.append(" ");
        }
        sb.append("]");
        return sb.toString();
    }
    
    public Examinator examinator() {
        return new Examinator(head);
    }
}
