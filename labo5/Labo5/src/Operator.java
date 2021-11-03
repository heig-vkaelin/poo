/**
 * Classe abstraite permettant de modéliser une opération arithmétique
 * entre deux entiers.
 *
 * @author Alexandre Jaquier, Valentin Kaelin
 */
public abstract class Operator {
    /**
     * Méthode abstraite à redéfinir pour spécifier le résultat de l'opération arithmétique
     * entre les deux opérandes.
     *
     * @param a premier opérande
     * @param b second opérande
     * @return le résultat de l'opération
     */
    public abstract int apply(int a, int b);
}
