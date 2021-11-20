package hanoi;

public class HanoiDisplayer {
    private static final String[] NUMBERS = {"One", "Two", "Three"};
    
    /**
     * Affiche l'état des aiguilles de l'instance de la classe Hanoi.
     * Par défaut l'affichage se fait dans la console.
     *
     * @param h instance du programme Hanoi en cours
     */
    public void display(Hanoi h) {
        StringBuilder msg = new StringBuilder();
        msg.append("-- Turn: ").append(h.turn()).append("\n");
        
        for (int i = 0; i < Hanoi.NB_NEEDLES; i++) {
            msg.append(String.format("%-5s", NUMBERS[i]))
                    .append(" : ")
                    .append(h.needleToString(i))
                    .append("\n");
        }
        System.out.print(msg);
    }
}
