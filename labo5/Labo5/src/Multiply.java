/**
 * Classe représentant l'opération arithmétique de multiplication
 *
 * @author Alexandre Jaquier
 * @author Valentin Kaelin
 */
public class Multiply extends Operator {
    /**
     * Multiplie les deux opérandes
     *
     * @param a premier opérande
     * @param b second opérande
     * @return le résultat de la multiplication
     */
    @Override
    public int apply(int a, int b) {
        return a * b;
    }
}
