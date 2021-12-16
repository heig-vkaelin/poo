package ExPoulailler;

import java.util.LinkedList;
import java.util.Random;

public class Poulailler {
    private int tour;
    private final LinkedList poules = new LinkedList();
    private final Random random = new Random();
    
    public void ajouter(Poule p) { // Ajout d’une poule dans le poulailler
        poules.add(p);
    }
    
    public void tourSuivant() {
        System.out.println("-- Tour #" + ++tour);
        LinkedList<Oeuf> oeufs = new LinkedList<>();
        
        // Ponte
        for (Object poule : poules) {
            int nbOeufs = random.nextInt(3);
            for (int i = 0; i < nbOeufs; ++i)
                oeufs.add(((Poule) poule).pondre());
        }
        
        // Éclosion
        for (Oeuf oeuf : oeufs)
            ajouter((Poule) oeuf.eclore());
    }
}
