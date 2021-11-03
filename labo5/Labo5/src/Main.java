/**
 * Classe principale du programme permettant de tester l'implémentation
 * des Matrices et des diverses opérations arithmétiques.
 *
 * @author Alexandre Jaquier
 * @author Valentin Kaelin
 */
public class Main {

    private static void testApply()  throws RuntimeException {
        System.out.println("TEST : Opération");
        int modulus1 = 4;
        int[][] data1 = new int[][]{
                {1,1,1,1},
                {1,1,1,1}
        };
        int modulus2 = 6;
        int[][] data2 = new int[][]{
                {1,1,1,1},
                {1,1,1,1}
        };
        Matrix m1 = new Matrix(data1, modulus1);
        Matrix m2 = new Matrix(data2, modulus2);
        try {
            Matrix m3 = Matrix.add(m1,m2);

        } catch (RuntimeException e){
            System.out.println("Erreur sortie lorsque les modulos durant une opération ne " +
                    "correspondent pas: ");
            System.out.println(e.getMessage() + "\n");
        }
    }

    private  static void testMatriceVide(){
        System.out.println("TEST : Matrice vide");
        int modulus = 4;
        int[][] data3 = new int[][]{
                {2,2,2}
        };
        int[][] data4 = new int[][]{
        };

        Matrix m3 = new Matrix(data3,modulus);
        System.out.println("Matrice vide :");
        try {
            Matrix m4 = new Matrix(data4,modulus);
            System.out.print(Matrix.add(m3, m4));
            System.out.println("Le test avec une matrice vide s'est bien passé.\n");
        } catch (RuntimeException e){
            System.out.println("Erreur une addition avec une matrice vide ne devrait pas lever " +
                    "d'exception\n");
        }
    }

    private static void testModuloNégatif()  throws RuntimeException {
        System.out.println("TEST : Modulo négatif");
        int modulus = -4;
        int[][] data1 = new int[][]{
                {1,1,1,1},
                {1,1,1,1}
        };
        try {
            Matrix m1 = new Matrix(data1, modulus);
        } catch (RuntimeException e){
            System.out.println("Erreur lors d'une création d'une matrice avec un modulo négatif: ");
            System.out.println(e.getMessage());
            System.out.println("TEST PASSÉS\n");
        }
    }

    private static void tesligneNégatif()  throws RuntimeException {
        System.out.println("TEST : Ligne de taille négative");
        int modulus = 4;
        int col = -1;
        int row = 1;
        try {
            Matrix m1 = new Matrix(col,row, modulus);
        } catch (RuntimeException e){
            System.out.println("Erreur lors d'une création d'une matrice avec une taille " +
                    "négative:");
            System.out.println(e.getMessage());
            System.out.println("TEST PASSÉS\n");
        }
    }

    private static void testligneTailleDifferentes()  throws RuntimeException {
        System.out.println("TEST : Ligne de taille différentes");
        int modulus = 4;
        int[][] data1 = new int[][]{
                {1,1,1,1},
                {1,1,1},
                {1,1},
                {1}
        };
        try {
            Matrix m1 = new Matrix(data1, modulus);
        } catch (RuntimeException e){
            System.out.println("Erreur lors d'une création d'une matrice avec des lignes de " +
                    "tailles différentes: ");
            System.out.println(e.getMessage());
            System.out.println("TEST PASSÉS\n");
        }
    }

    private static void testValeursIllegales()  throws RuntimeException {
        System.out.println("TEST : Valeures illégales");
        int modulus = 2;
        int[][] data1 = new int[][]{
                {1,1,1,1},
                {2,2,2,2}
        };
        try {
            Matrix m1 = new Matrix(data1, modulus);
        } catch (RuntimeException e){
            System.out.println("Erreur lors d'une création d'une matrice avec des valeures plus " +
                    "grandes que le modulo-1 : ");
            System.out.println(e.getMessage());
            System.out.println("TEST PASSÉS\n");
        }
    }


    /**
     * Méthode appelée au lancement du programme
     *
     * @param args arguments du programme (non utilisés ici)
     */
    public static void main(String[] args) {

        int modulus = 5;
        System.out.println("The modulus is " + modulus);

        int[][] data1 = new int[][]{
                {1, 3, 1, 1},
                {3, 2, 4, 2},
                {1, 0, 1, 0},
        };
        Matrix m1 = new Matrix(data1, modulus);
        System.out.println("one");
        System.out.println(m1);

        int[][] data2 = new int[][]{
                {1, 4, 2, 3, 2},
                {0, 1, 0, 4, 2},
                {0, 0, 2, 0, 2},
        };
        Matrix m2 = new Matrix(data2, modulus);
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
