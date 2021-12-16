package ExPoulailler;

interface Oeuf {
    Object eclore();
}

public class Poule {
    private final String nom;
    private int nbPontes;
    
    public Poule(String s) {
        nom = s;
    }
    
    public Oeuf pondre() {
        System.out.println(nom + " pond son oeuf #" + ++nbPontes);
        
        return new Oeuf() {
            private final int no = nbPontes;
            
            @Override
            public Object eclore() {
                String nouveauNom = nom + no;
                System.out.println(nouveauNom + " sort de l'oeuf #" + no + " de " + nom);
                return new Poule(nouveauNom);
            }
        };
    }
}
