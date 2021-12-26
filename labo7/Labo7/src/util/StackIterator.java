package util;

/**
 * Classe représentant un itérateur pointant sur un élément.
 *
 * @author Jonathan Friedli
 * @author Valentin Kaelin
 */
public class StackIterator {
    private Element element;
    
    /**
     * Constructeur de l'itérateur pointant sur le 1er élément
     *
     * @param element 1er élément
     */
    public StackIterator(Element element) {
        this.element = element;
    }
    
    /**
     * Vérifie que l'élément pointé existe
     *
     * @return true si l'élément existe, false sinon
     */
    public boolean hasNext() {
        return element != null;
    }
    
    /**
     * Fait avancer l'élément à l'élément suivant
     *
     * @return la valeur de l'élément courant
     * @throws RuntimeException s'il n'y a pas de prochain élément
     */
    public Object next() {
        if (!hasNext())
            throw new RuntimeException("Il n'y a pas d'élément suivant!");
        
        Element current = element;
        element = element.next;
        return current.value;
    }
}
