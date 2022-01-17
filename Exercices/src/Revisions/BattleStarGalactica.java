package Revisions;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

class Humain {
    private final String nom;
    private final List<Object> souvenirs = new LinkedList();
    
    public Humain(String nom) {
        this.nom = nom;
    }
    
    public void ajouterSouvenir(String s) {
        souvenirs.add(s);
    }
    
    @Override
    public boolean equals(Object obj) {
        return obj == this;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(nom);
    }
}

class Modele {
    private final String nom;
    private int counter = 0;
    
    public Modele(int id) {
        this.nom = "n°" + id;
    }
    
    public int getNumeroSerie() {
        return ++counter;
    }
}

class Cylon extends Humain implements Cloneable {
    private final Modele modele;
    private final int numeroDeSerie;
    
    public Cylon(String nom, Modele modele) {
        super(nom);
        this.modele = modele;
        numeroDeSerie = modele.getNumeroSerie();
    }
    
    @Override
    public Cylon clone() {
        Cylon c = null;
        try {
            c = (Cylon) super.clone();
            c.ajouterSouvenir("COPIE de " + numeroDeSerie);
        } catch (CloneNotSupportedException ignored) {
        }
        return c;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Cylon cylon = (Cylon) o;
        return modele.equals(cylon.modele);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(modele);
    }
}

public class BattleStarGalactica {
    public static void main(String... args) {
        Modele n6 = new Modele(6);
        Modele n8 = new Modele(8);
        Cylon boomer = new Cylon("Boomer", n8);
    }
}
