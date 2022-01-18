package Revisions;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

class Humain implements Cloneable {
    private final String nom;
    private List<Object> souvenirs = new LinkedList();
    
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
        return Objects.hash(nom); // truc random serait quasi mieux, po très important
    }
    
    @Override
    public Humain clone() {
        Humain h = null;
        try {
            h = (Humain) super.clone();
            h.souvenirs = new LinkedList<>(souvenirs);
        } catch (CloneNotSupportedException ignored) {
        }
        return h;
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

class Cylon extends Humain {
    private final Modele modele;
    private final int numeroDeSerie;
    
    public Cylon(String nom, Modele modele) {
        super(nom);
        this.modele = modele;
        numeroDeSerie = modele.getNumeroSerie();
    }
    
    @Override
    public Cylon clone() {
        Cylon c = (Cylon) super.clone();
        c.ajouterSouvenir("COPIE de " + numeroDeSerie);
        return c;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
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
        boomer.ajouterSouvenir("Boomer 1");
        boomer.ajouterSouvenir("Boomer 2");
        boomer.ajouterSouvenir("Boomer 3");
        Cylon clone = boomer.clone();
        boomer.ajouterSouvenir("Boomer 4");
        clone.ajouterSouvenir("Clone 1");
    }
}
