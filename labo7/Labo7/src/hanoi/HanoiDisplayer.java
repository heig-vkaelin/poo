package hanoi;

/**
 * Classe permettant l’affichage des étapes de résolution du problème dans la console
 *
 * @author Jonathan Friedli
 * @author Valentin Kaelin
 */
public class HanoiDisplayer {
    private static final String[] NUMBERS = {"One", "Two", "Three"};
    
    /**
     * Transforme un chiffre dans sa représentation en anglais
     *
     * @param number chiffre à transformer
     * @return la représentation du chiffre
     */
    public String numberToWord(int number) {
        if (number >= NUMBERS.length)
            throw new RuntimeException("Index invalide!");
        
        return NUMBERS[number];
    }
    
    /**
     * Affiche l'état des aiguilles de l'instance de la classe Hanoi.
     * Par défaut l'affichage se fait dans la console.
     *
     * @param h instance du programme Hanoi en cours
     */
    public void display(Hanoi h) {
        System.out.println(h);
    }
}
