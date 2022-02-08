package Revisions.Examen;

abstract class Champignon {
    private boolean estPoison;
    
    public Champignon() {
        this(true);
    }
    
    public Champignon(boolean estPoison) {
        this.estPoison = estPoison;
    }
    
    public void cueillir() {
        ramasser();
        if (estPoison)
            System.out.println("Je ricane doucement");
        stocker();
        cuisiner();
        manger();
    }
    
    protected abstract void stocker();
    
    protected void ramasser() {
        System.out.println("Je suis ramassé(e)");
    }
    
    protected void cuisiner() {
        System.out.println("Je suis cuisiné(e)");
    }
    
    protected void manger() {
        System.out.println("Tu es mort(e)");
    }
}

class AmaniteTueMouches extends Champignon {
    public void stocker() {
        System.out.println("J'attends mon heure");
    }
}

abstract class ChampignonComestible extends Champignon {
    private Provenance provenance;
    
    public ChampignonComestible(Provenance provenance) {
        super(false);
        this.provenance = provenance;
    }
    
    @Override
    protected void ramasser() {
        super.ramasser();
        System.out.println("Ma provenance: " + provenance.getPays());
    }
    
    @Override
    protected void manger() {
        System.out.println("Je suis un bon champignon!");
    }
}

class ChampignonDeParis extends ChampignonComestible {
    public ChampignonDeParis(Provenance provenance) {
        super(provenance);
    }
    
    public void stocker() {
        System.out.println("Je suis mis au frais");
    }
}


class Provenance {
    private String pays;
    
    public Provenance(String pays) {
        this.pays = pays;
    }
    
    public String getPays() {
        return pays;
    }
}

public class Champignons {
    public static void main(String... args) {
        Champignon amtm = new AmaniteTueMouches();
        ChampignonComestible cdp = new ChampignonDeParis(new Provenance("France"));
        
        amtm.cueillir();
        // Je suis ramassé(e)
        // Je ricane doucement
        // J'attends mon heure
        // Je suis cuisiné(e)
        // Tu es mort(e)
        
        System.out.println("\n----------\n");
        
        cdp.cueillir();
        // Je suis ramassé(e)
        // Ma provenance: France
        // Je suis mis au frais
        // Je suis cuisiné(e)
        // Je suis un bon champignon!
    }
}
