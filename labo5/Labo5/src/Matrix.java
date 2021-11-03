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
     * Constructeur privé utilisé pour factoriser la vérification du modulo
     *
     * @param mod modulo de la matrice
     * @throws RuntimeException si le modulo n'est pas strictement positif
     */
    private Matrix(int mod) throws RuntimeException {
        if (mod <= 0) {
            throw new RuntimeException("Les modulos négatifs ou égaux à 0 sont interdits.");
        }
        modulus = mod;
    }
    
    /**
     * Constructeur de Matrice remplie de valeurs aléatoires selon le modulo choisi.
     *
     * @param rows    nombre de lignes de la Matrice
     * @param columns nombre de colonnes de la Matrice
     * @param mod     modulo de la matrice
     * @throws RuntimeException si le modulo n'est pas strictement positif
     * @throws RuntimeException si les dimensions de la matrice sont négatives
     */
    Matrix(int rows, int columns, int mod) throws RuntimeException {
        this(mod);
        
        if (rows < 0 || columns < 0) {
            throw new RuntimeException("Le nombre de lignes et de colonnes de la matrice doivent " +
                    "être > 0.");
        }
        
        this.rows = rows;
        this.columns = columns;
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
     * @throws RuntimeException si le modulo n'est pas strictement positif
     * @throws RuntimeException si les lignes ne font pas toutes la même taille
     * @throws RuntimeException si une valeur n'est pas dans l'intervalle [0, modulo - 1]
     */
    Matrix(int[][] values, int mod) throws RuntimeException {
        this(mod);
        
        // Matrice vide
        if (values.length == 0) {
            rows = 0;
            columns = 0;
            data = new int[0][0];
            return;
        }
        
        rows = values.length;
        columns = values[0].length;
        data = new int[rows][columns];
        
        // Copie du tableau de valeurs
        for (int row = 0; row < rows; ++row) {
            if (values[row].length != columns) {
                throw new RuntimeException("Toutes les lignes de la matrice doivent avoir la " +
                        "même taille.");
            }
            for (int col = 0; col < columns; ++col) {
                if (values[row][col] < 0 || values[row][col] > (modulus - 1)) {
                    throw new RuntimeException("Les valeurs de la matrice doivent être entre 0 " +
                            "et modulo - 1.");
                } else {
                    data[row][col] = values[row][col];
                }
            }
        }
    }
    
    /**
     * Applique l'opération passée en paramètre entre les deux Matrices. L'opération sera faite
     * composante par composante et un modulo sera appliqué au résultat selon les modulos des
     * Matrices.
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
