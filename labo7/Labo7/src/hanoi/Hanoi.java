package hanoi;

import util.Stack;

public class Hanoi {
    public static void main(String[] args)throws RuntimeException {
    
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack);
    
        int numberOfDisk = testArgs(args);

        System.out.println("Hello World from Labo7!");
    }

    /**
     * Fonction permettant de tester que le paramètre passé par l'utilisateur est correct (un entier > 0).
     * @param args est le tableau d'argument passé par l'utilisateur.
     * @return La valeur passée par l'utilisateur, castée en int.
     * @throws RuntimeException
     */
    private static int testArgs(String[] args)throws RuntimeException {
        int numberOfDisk;
        if (args.length != 1) {
            throw new RuntimeException("Il ne faut qu'un seul argument (exemple: java Hanoi 7)");
        }
        try {
            numberOfDisk = Integer.parseInt(args[0]);
        } catch (Exception e) {
            throw new RuntimeException("L'argument doit être un entier.");
        }

        if (numberOfDisk < 1) {
            throw new RuntimeException("L'argument doit être un entier > 1");
        }
        return numberOfDisk;
    }

    /**
     * Constructeur générique
     *
     * @param disks
     * @param displayer
     */
    public Hanoi(int disks, HanoiDisplayer displayer) {

    }

    /**
     * Constructeur pour l'affichage de la console
     *
     * @param disks
     */
    public Hanoi(int disks) {

    }

    /**
     * Déplace tous les disques de la première aiguille à la troisième en
     * affichant les états successifs des aiguilles au moyen de l'instance
     * HanoiDisplayer sélectionnée.
     */
    public void solve() {

    }

    /**
     * Rend un tableau de tableaux représentant l'état des aiguilles. Pour un tel
     * tableau t, l'élément t[i][j] correspond à la taille du j-ème disque (en
     * partant du haut) de la i-ème aiguille.
     *
     * @return
     */
    public int[][] status() {
        return null;
    }

    /**
     * Permet de vérifier si la solution est atteinte
     *
     * @return true si la solution du problème a été atteinte, false sinon
     */
    public boolean finished() {
        return false;
    }

    /**
     * Rend le nombre de disques déplacés
     *
     * @return le nombre de disques déplacés
     */
    public int turn() {
        return 0;
    }
}
