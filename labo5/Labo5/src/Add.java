/*
 * Fichier     : Add.java
 * Auteurs     : Alexandre Jaquier, Valentin Kaelin
 * Description : Classe représentant l'opération arithmétique d'addition
 * Date        : 30.10.2021
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
