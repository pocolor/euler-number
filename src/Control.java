import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * The {@code Control} class provides methods to read and validate the value of the mathematical constant 'e'
 * from a control file. It allows caching of the value for efficiency and offers methods to verify the
 * correctness of a computed Euler number against the control value. Source of the correct value of 'e':
 * <a href="https://apod.nasa.gov/htmltest/gifcity/e.1mil">https://apod.nasa.gov/htmltest/gifcity/e.1mil</a>
 *
 * <p>The control value of 'e' to 1 000 022 decimal places is stored in a text file.</p>
 *
 * @author Luděk Vecsey
 */
public final class Control {
    private static final String controlPathname = "./control_e.txt";  // Path to the control file
    private static final File controlFile = new File(controlPathname);
    private static String cachedE = null;

    /**
     * Retrieves the control value of 'e' up to the specified length.
     * If the requested length is already cached, it returns the cached value.
     *
     * @param length the number of characters of 'e' to retrieve (must be > 0 and <= 1 000 022)
     * @return a string containing the first {@code length} characters of the control value of 'e'
     */
    public static String getControlE(int length) {
        assert length > 0 && length <= 1_000_022;  // 1 012 522 chars - 12 500 line breaks

        if (cachedE != null && length <= cachedE.length()) {
            return cachedE.substring(0, length);
        }

        StringBuilder controlE = new StringBuilder(length);

        try (BufferedReader br = new BufferedReader(new FileReader(controlFile))) {
            String line;
            while ((line = br.readLine()) != null && controlE.length() < length) {
                controlE.append(line);
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }

        return controlE.substring(0, length);
    }

    /**
     * Caches the control value of 'e' up to the specified length for future use.
     *
     * @param length the number of characters of 'e' to cache (must be > 0 and <= 1 000 022)
     */
    public static void cacheE(int length) {
        cachedE = getControlE(length);
    }

    /**
     * Compares the computed value of 'e' with the control value to check for correctness.
     *
     * @param eulerNumber the computed Euler number to validate
     * @return {@code true} if the computed value matches the control value, {@code false} otherwise
     */
    public static boolean isCorrect(EulerNumber eulerNumber) {
        String e = eulerNumber.toString();
        String correct = getControlE(e.length());
        return correct.equals(e);
    }

    /**
     * Retrieves the longest correct initial segment of the computed Euler number that matches the control value.
     *
     * @param eulerNumber the computed Euler number
     * @return a string representing the longest correct initial segment of the computed Euler number
     */
    public static String getFirstCorrectPart(EulerNumber eulerNumber) {
        char[] e = eulerNumber.toString().toCharArray();
        char[] correct = getControlE(e.length).toCharArray();

        int lastCorrect = 0;
        while (e[lastCorrect] == correct[lastCorrect]) {
            lastCorrect++;
        }

        return eulerNumber.toString().substring(0, lastCorrect);
    }
}
