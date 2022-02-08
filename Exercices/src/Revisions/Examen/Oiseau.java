package Revisions.Examen;

interface Volant {
    void voler();
}

interface Prédateur {
    void attaquer();
}

public class Oiseau implements Volant {
    public String type() {
        return "Je suis du type " + getClass().getName();
    }
    
    public void voler() {
        System.out.println("Je vole!");
    }
    
    // Main ici
    public static void main(String[] args) {
        Rapace rapace = new Rapace();
        Aigle aigle = new Aigle();
        Faucon faucon = new Faucon();
        
        // 1.
        Oiseau o = (Rapace) faucon;
        System.out.println(o.type());
        
        // 2.
        Volant v = faucon;
        v.voler();
        
        // 3.
        faucon.volerAvec(aigle);
        
        // 4.
//         ((Oiseau) rapace).attaquer();
        
        // 5.
//        Oiseau o2 = faucon;
//        rapace = o2;
        
        // 6.
        faucon.attaquer((Rapace) aigle);
        
        // 7.
        faucon.volerAvec((Faucon) rapace);
        
        // 8.
//        rapace.attaquer((Oiseau) aigle);
    }
}

class Rapace extends Oiseau implements Prédateur {
    public void attaquer() {
        System.out.println("Sus à l'ennemi!");
    }
    
    public void attaquer(Prédateur autre) {
        attaquer();
        autre.attaquer();
    }
    
    public void volerAvec(Rapace autre) {
        voler();
        autre.voler();
    }
}

class Aigle extends Rapace {
    public void attaquer() {
        System.out.println("A l'attaque!");
    }
}

class Faucon extends Rapace {
    public void voler() {
        System.out.println("Ca plane pour moi!");
    }
}
