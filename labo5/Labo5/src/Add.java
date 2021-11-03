/**
 * Classe représentant l'opération arithmétique d'addition
 *
 * @author Alexandre Jaquier, Valentin Kaelin
 */
public class Add extends Operator {
    /**
     * Additionne les deux opérandes
     *
     * @param a premier opérande
     * @param b second opérande
     * @return le résultat de l'addition
     */
    @Override
    public int apply(int a, int b) {
        return a + b;
    }
}
