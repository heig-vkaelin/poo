package Revisions.Examen;

import java.util.LinkedList;
import java.util.List;

enum Incident {Incendie, Panne, Innondation, Intrusion}

public class Detecteur {
    private int numero;
    private String lieu;
    private Incident type;
    private static int counter = 0;
    
    public Detecteur(String lieu, Incident type) {
        this.lieu = lieu;
        this.type = type;
        this.numero = ++counter;
    }
    
    public void signalerIncident() {
        System.out.println("Beep! Alarme du " + this + ".");
    }
    
    public String nom() {
        return "détecteur #" + numero;
    }
    
    public String lieu() {
        return "(" + lieu + ")";
    }
    
    public String type() {
        return "type: " + type;
    }
    
    @Override
    public String toString() {
        return nom() + " " + lieu() + ", " + type();
    }
    
    public static void main(String[] args) {
        // Point 1
//        Detecteur d = new Detecteur("HEIG-VD", Incident.Incendie);
//        System.out.println(d);
//        d.signalerIncident();
        
        // Point 2
        Centrale
                police = new Centrale("Police"),
                securitas = new Centrale("Securitas");
        
        Detecteur
                d1 = police.installerDetecteur("Immeuble", Incident.Panne),
                d2 = police.installerDetecteur("Immeuble", Incident.Incendie),
                d3 = police.installerDetecteur("Stade", Incident.Incendie),
                d4 = police.installerDetecteur("Stade", Incident.Innondation),
                d5 = securitas.installerDetecteur("Maison", Incident.Intrusion),
                d6 = securitas.installerDetecteur("Immeuble", Incident.Intrusion);
        
        d1.signalerIncident();
        d5.signalerIncident();
        d4.signalerIncident();
        
        police.afficher();
        securitas.afficher();
    }
}

class Centrale {
    private String nom;
    private List<Detecteur> detecteursSignales = new LinkedList<>();
    
    public Centrale(String nom) {
        this.nom = nom;
    }
    
    public Detecteur installerDetecteur(String lieu, Incident type) {
        return new Detecteur(lieu, type) {
            @Override
            public void signalerIncident() {
                super.signalerIncident();
                System.out.println("Centrale " + nom + ": alerte du " + nom() + " enregistrée\n");
                
                detecteursSignales.add(this);
            }
        };
    }
    
    public void afficher() {
        System.out.println("Alarmes de la centrale " + nom + ":");
        for (Detecteur d : detecteursSignales) {
            System.out.println("- " + d);
        }
        System.out.println();
    }
}
