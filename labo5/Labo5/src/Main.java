
public class Main {

    public static void main(String[] args) {
        int[][] data = new int[][]{
                {1,1,1,1},
                {2,2,2,2},
                {3,3,3,3},
                {4,4,4,4}
        };
        int[][] data2 = new int[][]{
                {0,0,0,0},
                {1,1,1,1},
                {3,3,3,3},
                {4,4,4,4}
        };
        Matrix m1 = new Matrix(data,16);
        Matrix m2 = new Matrix(data2,16);
        System.out.println("Matrice 1\n" + m1);
        System.out.println("Matrice 2\n" + m2);
        System.out.println("addition\n" + Matrix.add(m1,m2));
        System.out.println("soustraction\n" + Matrix.substract(m1,m2));
        System.out.println("multiplication\n" + Matrix.multiply(m1,m2));
    }
}
