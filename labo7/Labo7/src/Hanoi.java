import hanoi.gui.JHanoi;

/**
 *
 */
public class Hanoi {
    /**
     * @param args
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            new JHanoi();
        } else {
            hanoi.Hanoi hanoi = new hanoi.Hanoi(testArgs(args));
            hanoi.solve();
        }
    }
    
    /**
     * Fonction permettant de tester que le paramètre passé par l'utilisateur est correct (un entier > 0).
     *
     * @param args est le tableau d'argument passé par l'utilisateur.
     * @return La valeur passée par l'utilisateur, castée en int.
     * @throws RuntimeException
     */
    private static int testArgs(String[] args) {
        int numberOfDisk;
        // Si il y a 0 argument passé par l'utilisateur, on utilise l'interface graphique.
        if (args.length > 1)
            throw new RuntimeException("Il ne faut qu'un seul argument (exemple: java Hanoi 7)");
        
        try {
            numberOfDisk = Integer.parseInt(args[0]);
            
        } catch (Exception e) {
            throw new RuntimeException("L'argument doit être un entier positif.");
        }
        
        if (numberOfDisk < 0)
            throw new RuntimeException("L'argument doit être un entier positif");
        
        return numberOfDisk;
    }
    
    /**
     * Teste l'implémentation maison de la Stack
     */
    private static void testStack() {
    
    }
}
