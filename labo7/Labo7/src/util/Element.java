package util;

/**
 * Classe
 * @author Jonathan Friedli
 * @author Valentin Kaelin
 */
class Element {
    Object value;
    Element next;
    
    Element(Object value, Element next) {
        this.value = value;
        this.next = next;
    }
}
