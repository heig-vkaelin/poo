import java.util.Arrays;

public class Matrix {
    private int N;
    private int M;
    private int modulo;
    private int[][] data;
    
    Matrix(int m, int n, int mod) {
        N = n;
        M = m;
        modulo = mod;
        data = new int[M][N];
        java.util.Random random = new java.util.Random();
        for (int i = 0;i < data.length;++i){
            for (int j = 0;j < data[0].length;++j){
                data[i][j] = random.nextInt(modulo);
            }
        }

    }
    
    Matrix(int[][] values, int mod) {
        data = Arrays.copyOf(values,values.length);
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
        StringBuilder sb = new StringBuilder();
    
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(data[i][j]);
            }
            sb.append("\n");
        }
        
        return sb.toString();
    }
}
