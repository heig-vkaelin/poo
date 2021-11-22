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
     * Ajoute un élément au sommet de la pile
     *
     * @param value : valeur du nouvel élément
     */
    public void push(Object value) {
        top = new Element(value, top);
        ++size;
    }
    
    /**
     * Supprime et retourne l'élément au sommet de la pile
     *
     * @return l'élément supprimé
     * @throws RuntimeException si la pile est vide
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
     * Retourne la pile sous forme d'un tableau des valeurs contenues
     *
     * @return le tableau de valeurs
     */
    public Object[] state() {
        Object[] result = new Object[size];
        
        StackIterator i = iterator();
        int index = 0;
        while (i.hasNext()) {
            result[index++] = i.next();
        }
        return result;
    }
    
    /**
     * Itérateur sur la pile
     *
     * @return un itérateur commençant au sommet de la pile
     */
    public StackIterator iterator() {
        return new StackIterator(top);
    }
    
    /**
     * Retourne la représentation du contenu de la pile
     *
     * @return la représentation sous forme de chaîne de caractères
     */
    @Override
    public String toString() {
        StackIterator i = iterator();
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        while (i.hasNext()) {
            sb.append(" <");
            sb.append(i.next());
            sb.append("> ");
        }
        sb.append("]");
        return sb.toString();
    }
}
