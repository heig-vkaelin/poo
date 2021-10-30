/*
 * Fichier     : Multiply.java
 * Auteurs     : Alexandre Jaquier, Valentin Kaelin
 * Description : Classe représentant l'opération arithmétique de multiplication
 * Date        : 30.10.2021
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
