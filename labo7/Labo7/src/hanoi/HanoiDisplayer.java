package hanoi;

public class HanoiDisplayer {
    private static final String[] NUMBERS = {"One", "Two", "Three"};
    
    /**
     * Affiche l'état des aiguilles de l'instance de la classe Hanoi.
     * Par défaut l'affichage se fait dans la console.
     *
     * @param h
     */
    public void display(Hanoi h) {
        StringBuilder msg = new StringBuilder();
        msg.append("-- Turn: ").append(h.turn()).append("\n");
        int[][] status = h.status();
        for (int i = 0; i < status.length; i++) {
            msg.append(String.format("%-5s", NUMBERS[i])).append(" : [");
            for (int j = 0; j < status[i].length; ++j) {
                msg.append(" <").append(status[i][j]).append(">");
            }
            msg.append(" ]\n");
        }
        System.out.println(msg);
    }
}
