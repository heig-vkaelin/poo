import java.util.Arrays;

public class Main {
    
    public static void sort(Int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (arr[j].getValue() > arr[j + 1].getValue()) {
                    arr[j].swap(arr[j + 1]);
                    // Int.swapValues(arr[j], arr[j + 1]);
                    // Int.swapInArray(arr, j, j + 1);
                }
    }
    
    public static void main(String[] args) {
        Int[] values = new Int[args.length];
        
        for (int i = 0; i < args.length; i++) {
            int sign = 1, tmp = 0, j = 0;
            if (args[i].charAt(0) == '-') {
                sign = -1;
                j++;
            } else if (args[i].charAt(0) == '+') {
                j++;
            }
            for (; j < args[i].length(); j++) {
                tmp = tmp * 10 + Character.getNumericValue(args[i].charAt(j));
            }
            values[i] = new Int(tmp * sign);
        }
        
        sort(values);
        System.out.println(Arrays.toString(values));
    }
}
