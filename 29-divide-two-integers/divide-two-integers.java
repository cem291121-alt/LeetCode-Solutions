class Solution {
    public int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        long a = Math.abs((long) dividend);
        long b = Math.abs((long) divisor);
        long result = 0;

        while (a >= b) {
            long value = b;
            long quotient = 1;

            // Double the divisor until it would be too large
            while (a >= value + value) {
                value += value;
                quotient += quotient;
            }

            a -= value;
            result += quotient;
        }

        // The result is negative if the inputs have different signs
        if ((dividend < 0) != (divisor < 0)) {
            result = -result;
        }

        return (int) result;
    }
}