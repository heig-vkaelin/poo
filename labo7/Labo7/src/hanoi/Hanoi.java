package hanoi;

public class Hanoi {
    public static void main(String[] args) {
        System.out.println("Hello World from Labo7!");
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
