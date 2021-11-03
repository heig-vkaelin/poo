package matrix;

/**
 * Classe principale du programme permettant de tester l'implémentation
 * des Matrices et des diverses opérations arithmétiques.
 *
 * @author Alexandre Jaquier
 * @author Valentin Kaelin
 */
public class Main {
    /**
     * Teste qu'une exception est levée en cas d'opération entre deux matrices avec des
     * modules différents
     */
    private static void testDifferentModulus() {
        System.out.println("TEST : Opération avec modulos différents");
        int modulus1 = 4;
        int[][] data1 = new int[][]{
                {1, 1, 1, 1},
                {1, 1, 1, 1}
        };
        int modulus2 = 6;
        int[][] data2 = new int[][]{
                {1, 1, 1, 1},
                {1, 1, 1, 1}
        };
        Matrix m1 = new Matrix(data1, modulus1);
        Matrix m2 = new Matrix(data2, modulus2);
        try {
            Matrix.add(m1, m2);
            
        } catch (RuntimeException e) {
            System.out.println("Erreur levée lorsque les modulos ne correspondent pas: ");
            System.out.println(e.getMessage());
            System.out.println("TEST PASSÉ\n");
        }
    }
    
    /**
     * Teste que la création et les opérations avec une matrice vide se passent sans soucis
     */
    private static void testEmptyMatrix() {
        System.out.println("TEST : Matrice vide");
        int modulus = 4;
        int[][] data3 = new int[][]{
                {2, 2, 2}
        };
        int[][] data4 = new int[][]{};
        
        Matrix m3 = new Matrix(data3, modulus);
        System.out.println("Matrice vide multiplie une autre matrice :");
        try {
            Matrix m4 = new Matrix(data4, modulus);
            System.out.print(Matrix.multiply(m3, m4));
            System.out.println("TEST PASSÉ\n");
        } catch (RuntimeException e) {
            System.out.println("ERREUR: une addition avec une matrice vide ne devrait pas lever " +
                    "d'exception\n");
        }
    }
    
    /**
     * Teste qu'une exception est levée à la création d'une matrice avec un modulo négatif
     */
    private static void testNegativeModulus() {
        System.out.println("TEST : Modulo négatif");
        int modulus = -4;
        int[][] data1 = new int[][]{
                {1, 1, 1, 1},
                {1, 1, 1, 1}
        };
        try {
            new Matrix(data1, modulus);
        } catch (RuntimeException e) {
            System.out.println("Erreur levée lors d'une création d'une matrice avec un modulo " +
                    "négatif: ");
            System.out.println(e.getMessage());
            System.out.println("TEST PASSÉ\n");
        }
    }
    
    /**
     * Teste qu'une exception est levée à la création d'une matrice avec un nombre
     * de lignes négatif
     */
    private static void testNegativeRow() {
        System.out.println("TEST : Ligne de taille négative");
        int modulus = 4;
        int col = -1;
        int row = 1;
        try {
            new Matrix(col, row, modulus);
        } catch (RuntimeException e) {
            System.out.println("Erreur levée lors de la création d'une matrice d'une taille " +
                    "négative:");
            System.out.println(e.getMessage());
            System.out.println("TEST PASSÉ\n");
        }
    }
    
    /**
     * Teste qu'une exception est levée à la création d'une matrice avec des lignes
     * de taille différentes
     */
    private static void testRowsNotSameLength() {
        System.out.println("TEST : Lignes de tailles différentes");
        int modulus = 4;
        int[][] data1 = new int[][]{
                {1, 1, 1, 1},
                {1, 1, 1},
                {1, 1},
                {1}
        };
        try {
            new Matrix(data1, modulus);
        } catch (RuntimeException e) {
            System.out.println("Erreur levée lors de la création d'une matrice avec des lignes de" +
                    "tailles différentes: ");
            System.out.println(e.getMessage());
            System.out.println("TEST PASSÉ\n");
        }
    }
    
    /**
     * Teste qu'une exception est levée à la création d'une matrice des valeurs en dehors de
     * l'intervalle autorisé.
     */
    private static void testIllegalValues() {
        System.out.println("TEST : Valeurs illégales");
        int modulus = 2;
        int[][] data1 = new int[][]{
                {1, 1, 1, 1},
                {2, 2, 2, 2}
        };
        try {
            new Matrix(data1, modulus);
        } catch (RuntimeException e) {
            System.out.println("Erreur levée lors de la création d'une matrice avec des valeurs " +
                    "plus grandes que le modulo - 1 : ");
            System.out.println(e.getMessage());
            System.out.println("TEST PASSÉ\n");
        }
    }
    
    /**
     * Lance tous les tests de cas limites
     */
    public static void testEverything() {
        testDifferentModulus();
        testEmptyMatrix();
        testNegativeModulus();
        testNegativeRow();
        testRowsNotSameLength();
        testIllegalValues();
    }
    
    /**
     * Méthode appelée au lancement du programme, teste les différentes opérations sur des matrices
     * de valeurs aléatoires créées en fonction des arguments entrés par l'utilisateur.
     *
     * @param args 5 arguments du programme:
     *             - modulo
     *             - nbLignesMatrice1
     *             - nbColonnesMatrice1
     *             - nbLignesMatrice2
     *             - nbColonnesMatrice2
     * @throws Exception si l'entrée utilisateur est invalide
     */
    public static void main(String[] args) throws Exception {
        // Enlever le commentaire si l'on souhaite tester les différents cas limites
        // testEverything();
        
        if (args.length != 5) {
            String errorMsg = "Le programme demande 5 arguments:\n" +
                    "1. modulo\n" +
                    "2. nbLignesMatrice1\n" +
                    "3. nbColonnesMatrice1\n" +
                    "4. nbLignesMatrice2\n" +
                    "5. nbColonnesMatrice2";
            throw new Exception(errorMsg);
        }
        
        int modulus;
        int rows1;
        int cols1;
        int rows2;
        int cols2;
        
        try {
            modulus = Integer.parseInt(args[0]);
            rows1 = Integer.parseInt(args[1]);
            cols1 = Integer.parseInt(args[2]);
            rows2 = Integer.parseInt(args[3]);
            cols2 = Integer.parseInt(args[4]);
        } catch (NumberFormatException e) {
            // On relance une exception afin de stopper le programme tout en affichant un message
            // d'erreur explicite à l'utilisateur.
            throw new Exception("Les arguments entrés ne sont pas valides.", e);
        }
        
        System.out.println("The modulus is " + modulus);
        
        Matrix m1 = new Matrix(rows1, cols1, modulus);
        System.out.println("one");
        System.out.println(m1);
        
        Matrix m2 = new Matrix(rows2, cols2, modulus);
        System.out.println("two");
        System.out.println(m2);
        
        System.out.println("one + two");
        System.out.println(Matrix.add(m1, m2));
        
        System.out.println("one - two");
        System.out.println(Matrix.subtract(m1, m2));
        
        System.out.println("one x two");
        System.out.println(Matrix.multiply(m1, m2));
    }
}
