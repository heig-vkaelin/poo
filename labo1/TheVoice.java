import java.util.Random;

/**
 * Program modelling TheVoice game with a random voting system
 */
public class TheVoice {
    /**
     * Calculates the percentage for a given value and total
     *
     * @param value
     * @param total: maximum value (= 100%)
     * @return the percentage in integer value
     */
    public static int getIntegerPercent(int value, int total) {
        return value * 100 / total;
    }

    /**
     * Entry point of the Program
     *
     * @param args: array of command line arguments
     */
    public static void main(String[] args) {
        String[] candidates = args;
        final int NB_CANDIDATES = candidates.length;
        final int NB_VOTES = 150;

        if (NB_CANDIDATES == 0) {
            System.out.println("Il n'existe pas de candidats !");
            return;
        }

        System.out.println("Candidats:");
        for (int i = 0; i < NB_CANDIDATES; ++i) {
            System.out.println(String.format("#%s %s", i + 1, candidates[i]));
        }
        System.out.println("\n" + NB_VOTES + " votes:");
        System.out.println(".".repeat(NB_VOTES));

        System.out.println("\nRésultats:");
        int[] results = new int[NB_CANDIDATES];
        Random random = new Random();
        for (int i = 0; i < NB_VOTES; ++i) {
            results[random.nextInt(NB_CANDIDATES)]++;
        }
        for (int i = 0; i < NB_CANDIDATES; ++i) {
            System.out.println(
                    String.format("%s%% %s", getIntegerPercent(results[i], NB_VOTES), candidates[i])
            );
        }
    }
}
