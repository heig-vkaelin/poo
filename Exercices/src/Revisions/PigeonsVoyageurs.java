package Revisions;

class Oiseau {
    private final String nom;
    
    public String nom() {
        return nom;
    }
    
    public Oiseau(String nom) {
        this.nom = nom;
    }
    
    public void voler() {
        System.out.print("Flap!");
    }
    
    @Override
    public String toString() {
        return nom;
    }
}

class Pigeonnier {
    private static int counter = 0;
    private final String nom;
    
    public Pigeonnier(String nom) {
        this.nom = nom;
    }
    
    Oiseau obtenirPigeon(Pigeonnier destination, String msg) {
        return new Oiseau("Pigeon#" + ++counter) {
            boolean home = true;
            
            @Override
            public void voler() {
                home = !home;
                super.voler();
                String msgVol = home ? "arrive à la maison" : "dit: " + msg;
                System.out.println(" " + nom() + " " + msgVol);
            }
            
            @Override
            public String toString() {
                String position = home ? Pigeonnier.this.nom : destination.nom;
                return super.toString() + " se trouve à " + position;
            }
        };
    }
}

public class PigeonsVoyageurs {
    public static void main(String[] args) {
        Pigeonnier
                lausanne = new Pigeonnier("Lausanne"),
                geneve = new Pigeonnier("Genève"),
                yverdon = new Pigeonnier("Yverdon");
        Oiseau
                o1 = yverdon.obtenirPigeon(lausanne, "Bonjour Lausanne!"),
                o2 = geneve.obtenirPigeon(yverdon, "Bonjour Yverdon!");
        
        System.out.println("-- 1: 01: " + o1);
        System.out.println("-- 2: 01.voler()");
        o1.voler();
        System.out.println("-- 3: 01: " + o1);
        System.out.println("-- 4: 02: " + o2);
        System.out.println("-- 5: 02.voler()");
        o2.voler();
        System.out.println("-- 6: 02: " + o2);
        System.out.println("-- 7: 02.voler()");
        o2.voler();
        System.out.println("-- 8: 02: " + o2);
        System.out.println("-- 9: 01.voler()");
        o1.voler();
        System.out.println("-- 10: 01: " + o1);
        System.out.println("-- 11: 01.voler()");
        o1.voler();
        System.out.println("-- 12: 01: " + o1);
    }
}
