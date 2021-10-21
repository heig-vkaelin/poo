import java.util.Arrays;

public class Matrix {
    private int rows;
    private int columns;
    private final int modulo;
    private int[][] data;
    
    Matrix(int rows, int columns, int mod) {
        this.rows = rows;
        this.columns = columns;
        modulo = mod;
        data = new int[rows][columns];
        java.util.Random random = new java.util.Random();
        for (int row = 0; row < data.length; ++row) {
            for (int col = 0; col < data[0].length; ++col) {
                data[row][col] = random.nextInt(modulo);
            }
        }
        
    }
    
    Matrix(int[][] values, int mod) {
        modulo = mod;
        data = Arrays.copyOf(values, values.length);
    }
    
    public static Matrix add(Matrix m1, Matrix m2) {
        Operator op = new Add();
        return applyOperator(m1, m2, op);
    }
    
    public static Matrix substract(Matrix m1, Matrix m2) {
        Operator op = new Substract();
        return applyOperator(m1, m2, op);
    }
    
    public static Matrix multiply(Matrix m1, Matrix m2) {
        Operator op = new Multiply();
        return applyOperator(m1, m2, op);
    }
    
    private static Matrix applyOperator(Matrix m1, Matrix m2, Operator op) {
        if (m1.modulo != m2.modulo) {
            throw new RuntimeException("Les modulos des deux matrices ne correspondent pas.");
        }
        
        int maxRows = Math.max(m1.rows, m2.rows);
        int maxColumns = Math.max(m1.columns, m2.columns);
        int[][] result = new int[maxRows][maxColumns];
        
        for (int row = 0; row < maxRows; ++row) {
            for (int col = 0; col < maxColumns; ++col) {
                int op1 = row >= m1.rows || col >= m1.columns ? 0 : m1.data[row][col];
                int op2 = row >= m2.rows || col >= m2.columns ? 0 : m2.data[row][col];
                result[row][col] = Math.floorMod(op.apply(op1, op2), m1.modulo);
            }
        }
        
        return new Matrix(result, m1.modulo);
    }
    
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
