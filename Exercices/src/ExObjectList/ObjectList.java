package ExObjectList;

import javax.lang.model.util.ElementScanner6;

public class ObjectList {
    private Element head;
    private int size;
    
    public ObjectList(Object o) {
        head = new Element(o);
        size = 1;
    }
    
    private Element beforeHead() {
        return new Element(null, head);
    }
    
    public boolean isEmpty() {
        return head == null;
    }
    
    public int size() {
        return size;
    }
    
    public void insert(Object o) {
        ++size;
        head = new Element(o, head);
    }
    
    public void append(Object o) {
        ++size;
        Examinator ex = examinator();
        Element last = null;
        if (head == null) {
            insert(o);
            return;
        }
        while (ex.hasNext()) {
            last = (Element) ex.next();
        }
        last.setNext(new Element(o));
    }
    
    public void remove(Object o) {
        Examinator ex = examinator();
        Element e = beforeHead();
        while (ex.hasNext() && !e.getNext().getData().equals(o)) {
            e = (Element) ex.next();
        }
        
        if (e != null) {
            Element next = ex.hasNext() ? e.getNext().getNext() : null;
            if (ex.hasNext() && e.getNext().equals(head)) {
                head = next;
            }
            e.setNext(next);
            --size;
        }
    }
    
    public Object get(int index) throws RuntimeException {
        if (index >= size || index < 0) {
            throw new RuntimeException("Index invalide.");
        }
        
        int i = 0;
        Examinator ex = examinator();
        Element e = null;
        while (ex.hasNext() && i != index + 1) {
            e = (Element) ex.next();
            i++;
        }
        return e;
    }
    
    @Override
    public String toString() {
        Examinator i = examinator();
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        while (i.hasNext()) {
            Element e = (Element) i.next();
            sb.append(e);
            sb.append((e.getNext() == null ? "" : ", "));
        }
        sb.append("]");
        return sb.toString();
    }
    
    public Examinator examinator() {
        return new Examinator(beforeHead());
    }
}
