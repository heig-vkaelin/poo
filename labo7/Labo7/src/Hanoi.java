import hanoi.gui.JHanoi;

/**
 *
 */
public class Hanoi {
    /**
     * Méthode appelée au lancement du programme, teste le nombre de paramètre et la validité de ce dernier.
     * Si paramètre valide, résouds la tour de Hanoï avec le nombre de disque voulu.
     * @param args 0 ou 1:
     *             Si 0 paramètre, utilise l'interface graphique.
     *             Si 1 paramètre, utilise l'interface console. Le paramètre représente le nombre de disque.
     *             Il doit être positif.
     *             Si 2 paramètre ou plus, le programme lance une RuntimeException.
     * @throws Exception en cas d'entrée utilisateur incorrecte.
     */
    public static void main(String[] args) throws Exception {
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
     * @throws Exception
     */
    private static int testArgs(String[] args) throws Exception {
        int numberOfDisk;
        // Si il y a 0 argument passé par l'utilisateur, on utilise l'interface graphique.
        if (args.length > 1)
            throw new Exception("Il ne faut qu'un seul argument (exemple: java Hanoi 7)");
        
        try {
            numberOfDisk = Integer.parseInt(args[0]);
            
        } catch (Exception e) {
            throw new Exception("L'argument doit être un entier positif.");
        }
        
        if (numberOfDisk < 0)
            throw new Exception("L'argument doit être un entier positif");
        
        return numberOfDisk;
    }
    
    /**
     * Teste l'implémentation maison de la Stack
     */
    private static void testStack() {
    
    }
}
