import java.util.Arrays;

public class Matrix {
    private int N;
    private int M;
    private int modulo;
    private int[][] data;
    
    Matrix(int m, int n, int mod) {
    
    }
    
    Matrix(int[][] values, int mod) {
    
    }
    
    public static Matrix add(Matrix m1, Matrix m2) {
        return null;
    }
    
    public static Matrix substract(Matrix m1, Matrix m2) {
        return null;
    }
    
    public static Matrix multiply(Matrix m1, Matrix m2) {
        return null;
    }
    
    @Override
    public String toString() {
        // TODO
        return "Matrix{" +
                "data=" + Arrays.toString(data) +
                '}';
    }
}
