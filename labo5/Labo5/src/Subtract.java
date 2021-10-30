/*
 * Fichier     : Subtract.java
 * Auteurs     : Alexandre Jaquier, Valentin Kaelin
 * Description : Classe représentant l'opération arithmétique de soustraction
 * Date        : 30.10.2021
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
