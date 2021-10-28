
public class Main {
    
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
