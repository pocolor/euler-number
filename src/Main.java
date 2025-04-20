public class Main {
    public static void main(String[] args) {

        System.out.println("Euler number to 100 decimal places: " + new EulerNumber(100));

        // ---

        long time = System.currentTimeMillis();
        EulerNumber eulerNumber = new EulerNumber(100_000);
        time = System.currentTimeMillis() - time;

        System.out.println("\nEuler number to 100 000 decimal places calculated in " + Math.round((double) time / 1000) + " seconds.");
        System.out.println("Correct: " + Control.isCorrect(eulerNumber));

        // ---

        System.out.println("\nCalculating Euler number from 1 to 5000 decimal places until it finds 1 that's not correct.");

        int xSteps = 50;
        int step = 100;
        Control.cacheE(xSteps * step + 2);


        for (int i = 0; i < xSteps;) {
            for (int j = 1; j <= step; j++) {
                if (!Control.isCorrect(new EulerNumber(i * step + j))) {
                    System.out.println("Error: EulerNumber with " + (i * step + j) + " decimal places isn't correct.");
                    System.exit(1);
                }
            }

            System.out.println(++i * step + " decimal places calculated");
        }

        System.out.println("\nAll of them were correct.");
    }
}