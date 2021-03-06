package hanoi;

import util.Stack;

/**
 * Classe contenant toute la logique de la résolution du problème des tours d’Hanoi.
 * Elle s’occupe notamment de contenir les trois aiguilles ainsi que d’appliquer
 * l’algorithme récursif.
 *
 * @author Jonathan Friedli
 * @author Valentin Kaelin
 */
public class Hanoi {
    private static final int NB_NEEDLES = 3;
    
    private final Stack[] needles;
    private final int nbDisks;
    private final HanoiDisplayer displayer;
    
    private int turns;
    
    /**
     * Constructeur principal. Est appelé dans les deux versions du programme:
     * - La version graphique
     * - La version console
     *
     * @param disk      le nombre de disques sur l'aiguille
     * @param displayer l'affichage choisi (graphique / console)
     * @throws RuntimeException en cas de nombre de disques invalide
     */
    public Hanoi(int disk, HanoiDisplayer displayer) {
        if (disk < 0)
            throw new RuntimeException("Le nombre de disques ne peut pas être " +
                    "négatif.");
        
        nbDisks = disk;
        this.displayer = displayer;
        this.turns = 0;
        
        needles = new Stack[NB_NEEDLES];
        for (int i = 0; i < NB_NEEDLES; i++) {
            needles[i] = new Stack();
        }
        
        // Ajout des disques sur la 1ère aiguille
        for (int i = nbDisks; i > 0; --i) {
            needles[0].push(i);
        }
    }
    
    /**
     * Constructeur pour l'affichage de la console
     *
     * @param disk le nombre de disques sur l'aiguille
     */
    public Hanoi(int disk) {
        this(disk, new HanoiDisplayer());
    }
    
    /**
     * Déplace tous les disques de la première aiguille à la troisième en
     * affichant les états successifs des aiguilles au moyen de l'instance
     * HanoiDisplayer sélectionnée.
     */
    public void solve() {
        this.displayer.display(this);
        this.hanoiAlgorithm(nbDisks, needles[0], needles[1], needles[2]);
    }
    
    /**
     * Implémentation de l'algorithme d'Hanoi sous forme récursive
     *
     * @param nbDisks      nombre de disques
     * @param start        aiguille de départ
     * @param intermediate aiguille du centre
     * @param finish       aiguille d'arrivée
     */
    private void hanoiAlgorithm(int nbDisks, Stack start, Stack intermediate, Stack finish) {
        if (nbDisks > 0) {
            this.hanoiAlgorithm(nbDisks - 1, start, finish, intermediate);
            this.move(start, finish);
            this.hanoiAlgorithm(nbDisks - 1, intermediate, start, finish);
        }
    }
    
    /**
     * Déplace le disque supérieur de l'aiguille source à l'aiguille de destination
     *
     * @param from l'aiguille source
     * @param to   l'aiguille de destination
     */
    private void move(Stack from, Stack to) {
        to.push(from.pop());
        ++this.turns;
        this.displayer.display(this);
    }
    
    /**
     * Rend un tableau de tableaux représentant l'état des aiguilles. Pour un tel
     * tableau t, l'élément t[i][j] correspond à la taille du j-ème disque (en
     * partant du haut) de la i-ème aiguille.
     *
     * @return l'état de chaque aiguille
     */
    public int[][] status() {
        int[][] result = new int[NB_NEEDLES][];
        
        for (int i = 0; i < NB_NEEDLES; i++) {
            Object[] state = needles[i].state();
            result[i] = new int[state.length];
            
            for (int j = 0; j < state.length; j++) {
                result[i][j] = (int) state[j];
            }
        }
        return result;
    }
    
    /**
     * Permet de vérifier si la solution est atteinte
     *
     * @return true si la solution du problème a été atteinte, false sinon
     */
    public boolean finished() {
        return turns == Math.pow(2, nbDisks) - 1;
    }
    
    /**
     * Rend le nombre de disques déplacés
     *
     * @return le nombre de disques déplacés
     */
    public int turn() {
        return turns;
    }
    
    /**
     * Retourne la représentation sous forme de chaîne de caractères des
     * états actuels des aiguilles
     *
     * @return la représentation de l'état actuel
     */
    @Override
    public String toString() {
        StringBuilder state = new StringBuilder();
        state.append("-- Turn: ").append(turn()).append("\n");
        
        for (int i = 0; i < NB_NEEDLES; i++) {
            state.append(String.format("%-5s", displayer.numberToWord(i)))
                    .append(" : ")
                    .append(needles[i]);
            
            if (i < NB_NEEDLES - 1)
                state.append("\n");
        }
        return state.toString();
    }
}
