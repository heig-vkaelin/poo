import java.util.Arrays;
import java.util.Random;

/**
 * Classe permettant de modéliser une Matrice. Il est également possible de réaliser
 * diverses opérations arithmétiques sur deux Matrices.
 *
 * @author Alexandre Jaquier
 * @author Valentin Kaelin
 */
public class Matrix {
    private int rows;
    private int columns;
    private final int modulus;
    private int[][] data;
    
    /**
     * Constructeur de Matrice remplie de valeurs aléatoires selon le modulo choisi.
     *
     * @param rows    nombre de lignes de la Matrice
     * @param columns nombre de colonnes de la Matrice
     * @param mod     modulo de la matrice
     */
    Matrix(int rows, int columns, int mod) {
        this.rows = rows;
        this.columns = columns;
        modulus = mod;
        data = new int[rows][columns];
        Random random = new Random();
        
        for (int row = 0; row < data.length; ++row) {
            for (int col = 0; col < data[0].length; ++col) {
                data[row][col] = random.nextInt(modulus);
            }
        }
    }
    
    /**
     * Constructeur de Matrice via des valeurs déjà définies
     *
     * @param values tableau à deux dimensions contenant les futures valeurs de la Matrice
     * @param mod    modulo de la Matrice
     */
    Matrix(int[][] values, int mod) {
        modulus = mod;
        if (values.length > 0 && values[0].length > 0) {
            rows = values.length;
            columns = values[0].length;
            data = Arrays.copyOf(values, values.length);
        }
    }
    
    /**
     * Applique l'opération passée en paramètre entre les deux Matrices. L'opération sera faite composante
     * par composante et un modulo sera appliqué au résultat selon les modulos des Matrices.
     *
     * @param m1 première Matrice
     * @param m2 deuxième Matrice
     * @param op opération à effectuer entre les deux Matrices
     * @return une nouvelle instance de Matrice après l'opération effectuée
     * @throws RuntimeException si les modulos ne sont pas compatibles
     */
    private static Matrix applyOperator(Matrix m1, Matrix m2, Operator op) throws RuntimeException {
        if (m1.modulus != m2.modulus) {
            throw new RuntimeException("Les modulos des deux matrices ne correspondent pas.");
        }
        
        int maxRows = Math.max(m1.rows, m2.rows);
        int maxColumns = Math.max(m1.columns, m2.columns);
        int[][] result = new int[maxRows][maxColumns];
        
        for (int row = 0; row < maxRows; ++row) {
            for (int col = 0; col < maxColumns; ++col) {
                int op1 = row >= m1.rows || col >= m1.columns ? 0 : m1.data[row][col];
                int op2 = row >= m2.rows || col >= m2.columns ? 0 : m2.data[row][col];
                result[row][col] = Math.floorMod(op.apply(op1, op2), m1.modulus);
            }
        }
        
        return new Matrix(result, m1.modulus);
    }
    
    /**
     * Addition entre deux Matrices. L'addition sera faite composante par composante et
     * un modulo sera appliqué au résultat selon les modulos des Matrices.
     *
     * @param m1 première Matrice
     * @param m2 deuxième Matrice
     * @return une nouvelle instance de Matrice après l'addition effectuée
     * @throws RuntimeException si les modulos ne sont pas compatibles
     */
    public static Matrix add(Matrix m1, Matrix m2) throws RuntimeException {
        return applyOperator(m1, m2, new Add());
    }
    
    /**
     * Soustraction entre deux Matrices. La soustraction sera faite composante par composante et
     * un modulo sera appliqué au résultat selon les modulos des Matrices.
     *
     * @param m1 première Matrice
     * @param m2 deuxième Matrice
     * @return une nouvelle instance de Matrice après la soustraction effectuée
     * @throws RuntimeException si les modulos ne sont pas compatibles
     */
    public static Matrix subtract(Matrix m1, Matrix m2) throws RuntimeException {
        return applyOperator(m1, m2, new Subtract());
    }
    
    /**
     * Multiplication entre deux Matrices. La multiplication sera faite composante par composante et
     * un modulo sera appliqué au résultat selon les modulos des Matrices.
     *
     * @param m1 première Matrice
     * @param m2 deuxième Matrice
     * @return une nouvelle instance de Matrice après la multiplication effectuée
     * @throws RuntimeException si les modulos ne sont pas compatibles
     */
    public static Matrix multiply(Matrix m1, Matrix m2) throws RuntimeException {
        return applyOperator(m1, m2, new Multiply());
    }
    
    /**
     * Représentation de la Matrice
     *
     * @return une String représentant l'instance de la Matrice
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        for (int row = 0; row < rows; ++row) {
            for (int col = 0; col < columns; ++col) {
                sb.append(data[row][col]);
            }
            sb.append("\n");
        }
        
        return sb.toString();
    }
}
