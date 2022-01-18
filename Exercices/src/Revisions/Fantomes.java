package Revisions;

enum Lieu {Bree, Rivendell, Lorien, Moria};

abstract class Avatar {
    private String nom;
    private Lieu position;
    
    public Avatar(String nom, Lieu position) {
        this.nom = nom;
        this.position = position;
        System.out.println(nom + " se matérialise. Lieu: " + position);
    }
    
    public void deplacer(Lieu position) {
        System.out.println(nom + " se déplace. Lieu: " + position);
        this.position = position;
    }
    
    public String toString() {
        return nom + ", lieu: " + position;
    }
    
    public String nom() {
        return nom;
    }
    
    public Lieu position() {
        return position;
    }
}

class Equipement {
    private String nom;
    private int niveau;
    
    public Equipement(String nom, int niveau) {
        this.nom = nom;
        this.niveau = niveau;
    }
    
    public String nom() {
        return nom;
    }
}

class Personnage {

}

public class Fantomes {
    public static void main(String[] args) {
        System.out.println("- 0 -");
        Personnage gandalf = new Personnage("Gandalf", Lieu.Bree, 50);
    
        gandalf.equiper(new Equipement("Epée", 20));
        gandalf.equiper(new Equipement("Baton", 40));
    
        System.out.println("- 1 -");
        System.out.println(gandalf);
    
        System.out.println("- 2 -");
        System.out.println(Lieu.Moria);
    
        System.out.println("- 3 -");
        Avatar fantome = gandalf.mourir();
    
        System.out.println("- 4 -");
        System.out.println(fantome == gandalf.fantome());
    
        System.out.println("- 5 -");
        gandalf.deplacer(Lieu.Rivendell);
    
        System.out.println("- 6 -");
        fantome.deplacer(Lieu.Rivendell);
    
        System.out.println("- 7 -");
        fantome.deplacer(Lieu.Moria);
    
        System.out.println("- 8 -");
        System.out.println(gandalf.fantome() != null);
    
        System.out.println("- 9 -");
        gandalf.deplacer(Lieu.Lorien);
    }
}
