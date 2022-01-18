package Revisions;

import java.util.LinkedList;
import java.util.List;

enum Lieu {Bree, Rivendell, Lorien, Moria};

abstract class Avatar {
    private final String nom;
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
    private final String nom;
    private final int niveau;
    
    public Equipement(String nom, int niveau) {
        this.nom = nom;
        this.niveau = niveau;
    }
    
    public String nom() {
        return nom;
    }
}

class Personnage extends Avatar {
    private final int niveau;
    private final List<Equipement> equipements;
    private Avatar fantome;
    
    public Personnage(String nom, Lieu position, int niveau) {
        super(nom, position);
        this.niveau = niveau;
        equipements = new LinkedList<>();
    }
    
    public void equiper(Equipement equipement) {
        equipements.add(equipement);
    }
    
    public Avatar mourir() {
        if (fantome != null)
            return fantome;
        
        System.out.println(nom() + " meurt!");
        return fantome = new Avatar("Le fantôme de " + nom(), Lieu.Lorien) {
            @Override
            public void deplacer(Lieu position) {
                super.deplacer(position);
                if (position == Personnage.this.position()) {
                    System.out.println(nom() + " retrouve son corps et le ressucite!");
                    ressusciter();
                }
            }
        };
    }
    
    public Avatar fantome() {
        return fantome;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append(", niveau: ").append(niveau).append(",");
        for (Equipement e : equipements)
            sb.append(" ").append(e.nom());
        return sb.toString();
    }
    
    private void ressusciter() {
        fantome = null;
    }
    
    @Override
    public void deplacer(Lieu position) {
        if (fantome == null)
            super.deplacer(position);
        else
            System.out.println(nom() + " est mort et ne peut pas se déplacer...");
    }
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
        gandalf.deplacer(Lieu.Moria);
        
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
