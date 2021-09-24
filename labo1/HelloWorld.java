/**
 * Program printing some Hello World messages
 */
public class HelloWorld {
    /**
     * Entry point of the Program
     *
     * @param args: array of command line arguments
     * @throws java.lang.NumberFormatException in case of an error during the cast to integer
     */
    public static void main(String[] args) {
        int number;

        if (args.length == 0)
            number = 0;
        else
            number = Integer.parseInt(args[0]);

        for (int i = 0; i < number; ++i)
            System.out.println(i + " Hello World");
    }
}
