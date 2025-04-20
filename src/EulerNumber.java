import java.util.Arrays;

/**
 * The {@code EulerNumber} class calculates and represents the value of the mathematical constant 'e'
 * to a specified number of decimal places. The computation uses an iterative algorithm.
 *
 * <p>The value of 'e' is computed using the series expansion:
 * e = 1/0! + 1/1! + 1/2! + 1/3! + ...</p>
 *
 * @author Luděk Vecsey
 */
public class EulerNumber {
    private static final int BASE10 = 10;
    private final int decimalPlaces;
    private final int[] e;
    private final int[] term;
    private int firstNonNull;

    /**
     * Class constructor specifying the number of decimal places to compute.
     *
     * @param decimalPlaces The number of decimal places to compute. Must be greater than 0.
     *
     * @throws IllegalArgumentException If the {@code decimalPlaces} is less than or equal to 0.
     */
    public EulerNumber(int decimalPlaces) throws IllegalArgumentException {
        if (decimalPlaces <= 0) throw new IllegalArgumentException("Must have positive number of decimal places");

        this.decimalPlaces = decimalPlaces;
        int extraPrecision = (int) Math.max(1, Math.ceil(Math.log10(decimalPlaces))) * 2;

        this.e = new int[this.decimalPlaces + extraPrecision];
        this.term = new int[this.decimalPlaces + extraPrecision];

        this.calculate();
    }

    /**
     * Calculates the value of 'e' using an iterative algorithm.
     * The value is stored in the {@code e} array.
     */
    private void calculate() {
        this.e[0] = 5;  // 'e' value for 1/2!
        this.term[0] = 5;  // store 1/2! as the starting term
        this.firstNonNull = 0;
        int n = 2;

        while (this.nonNullTerm()) {
            n++;
            this.divideTerm(n);
            this.addTerm();
        }
    }

    /**
     * Checks if the current term in the series is non-null (it doesn't contain only nulls).
     *
     * @return {@code true} if the term is non-null, {@code false} otherwise
     */
    private boolean nonNullTerm() {
        for (int i = this.firstNonNull; i < this.term.length; i++) {
            if (this.term[i] != 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Divides the current term in the series by the next integer {@code n} in the series.
     * The result is the next term in the series and is stored in the {@code term} array.
     *
     * @param n the next integer in the series
     */
    private void divideTerm(int n) {
        int carry = 0;
        boolean nonNullFound = false;

        for (int i = this.firstNonNull; i < this.e.length; i++) {
            int current = carry * BASE10 + this.term[i];
            this.term[i] = current / n;
            carry = current % n;

            if (!nonNullFound && this.term[i] > 0) {
                nonNullFound = true;
                this.firstNonNull = i;
            }
        }
    }

    /**
     * Adds the current term in the series to the computed value of 'e'.
     */
    private void addTerm() {
        boolean carry = false;

        for (int i = this.e.length - 1; i >= this.firstNonNull || carry; i--) {
            int current = this.e[i] + this.term[i] + (carry ? 1 : 0);

            if (current >= BASE10) {
                this.e[i] = current - BASE10;
                carry = true;
            } else {
                this.e[i] = current;
                carry = false;
            }
        }
    }

    /**
     * Rounds the computed value of 'e' to the specified number of decimal places.
     */
    public void round() {
        int i = this.decimalPlaces;

        if (this.e[i] >= BASE10 / 2) {
            while (true) {
                int current = ++this.e[--i];

                if (current < BASE10) {
                    break;
                }

                this.e[i] = current - BASE10;
            }
            Arrays.fill(this.e, this.decimalPlaces, this.e.length, 0);
        }
    }

    /**
     * Returns a string representation of the computed value of 'e' up to the specified number of decimal places.
     *
     * @return a string representing the value of 'e'
     */
    @Override
    public String toString() {
        // since we store only the decimal places we have to add 2 for the integer part (2) and the decimal point
        StringBuilder sb = new StringBuilder(this.e.length + 2);
        sb.append("2.");

        for (int d : this.e) {
            sb.append(d);
        }

        return sb.substring(0, this.decimalPlaces + 2);
    }
}
