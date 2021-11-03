/**
 * Classe représentant l'opération arithmétique de soustraction
 *
 * @author Alexandre Jaquier
 * @author Valentin Kaelin
 */
public class Subtract extends Operator {
    /**
     * Soustrais la deuxième opérande à la première
     *
     * @param a premier opérande
     * @param b second opérande
     * @return le résultat de la soustraction
     */
    @Override
    public int apply(int a, int b) {
        return a - b;
    }
}
