package Revisions;

import java.util.LinkedList;

class Element {
    private final String nom;
    private final int id;
    private static int counter = 0;
    public static final Element
            fer = new Element("fer"),
            carbone = new Element("carbone"),
            mercure = new Element("mercure");
    
    private Element(String nom) {
        this.nom = nom;
        this.id = ++counter;
    }
    
    public static Element[] values() {
        return new Element[]{fer, carbone, mercure};
    }
    
    public String toString() {
        return nom + " {#" + id + "}";
    }
}

class Planete {
    private final String nom;
    private final Element[] elements;
    private int nbSondes;
    
    public Planete(String nom, Element... elements) {
        this.nom = nom;
        this.elements = new Element[elements.length];
        for (int i = 0; i < elements.length; i++) {
            this.elements[i] = elements[i];
        }
//        this.elements = elements.clone();
        nbSondes = 0;
    }
    
    public String nom() {
        return nom;
    }
    
    public boolean contient(Element e) {
        for (Element element : elements)
            if (e == element) return true;
        return false;
    }
    
    public Extracteur construireSonde(Element element) {
        Extracteur extracteur = new Extracteur(++nbSondes) {
            @Override
            public String nom() {
                return Planete.this.nom() + "-" + super.nom();
            }
            
            @Override
            public Element extraire() {
                System.out.print(nom() + " recherche " + element + " ... ");
                if (contient(element)) {
                    System.out.println("extrait !");
                    return element;
                }
                System.out.println("inconnu");
                return null;
            }
        };
        System.out.println("Construction de la sonde " + extracteur.nom());
        return extracteur;
    }
}

abstract class Extracteur {
    private final int id;
    
    public Extracteur(int id) {
        this.id = id;
    }
    
    public String nom() {
        return "" + id;
    }
    
    abstract public Object extraire();
}

public class Planetes {
    public static void main(String... args) {
        System.out.println("PARTIE 1");
        for (Element e : Element.values()) {
            System.out.println(e);
        }
        
        System.out.println("\nPARTIE 2");
        Planete mars1 = new Planete("Mars", Element.fer, Element.carbone);
        Element e = Element.mercure;
        System.out.println(mars1.nom() + ", " + e + ", " + (mars1.contient(e) ?
                "oui" : "non"));
        
        System.out.println("\nPARTIE 3");
        Planete mars2 = new Planete("Mars", Element.fer, Element.carbone);
        Planete venus = new Planete("Venus", Element.mercure);
        
        System.out.println("-- Construction des sondes");
        
        Extracteur[] sondes = {
                mars2.construireSonde(Element.mercure),
                mars2.construireSonde(Element.carbone),
                venus.construireSonde(Element.fer),
                venus.construireSonde(Element.mercure),
        };
        
        System.out.println("-- Extractions");
        
        LinkedList<Object> list = new LinkedList<>();
        for (Extracteur s : sondes) {
            Object o = s.extraire();
            if (o != null)
                list.add(o);
        }
        
        System.out.println("-- Eléments trouvés");
        System.out.println(list);
    }
}
