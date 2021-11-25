package util;

/**
 * Classe conteneur permettant de lier un élément avec un potentiel élément suivant
 *
 * @author Jonathan Friedli
 * @author Valentin Kaelin
 */
class Element {
    Object value;
    Element next;
    
    /**
     * Constructeur basique d'un élément
     *
     * @param value valeur de l'élément
     * @param next  élément suivant, null si aucun
     */
    Element(Object value, Element next) {
        this.value = value;
        this.next = next;
    }
}
