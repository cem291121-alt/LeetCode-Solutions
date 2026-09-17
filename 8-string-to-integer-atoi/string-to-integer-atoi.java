public class Solution {

    public int myAtoi(String s) {
        int i = 0;
        int n = s.length();
        int sign = 1;
        long result = 0;

        // 1. Ignore leading spaces
        while (i < n && s.charAt(i) == ' ') {
            i++;
        }

        // 2. Check sign
        if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
            if (s.charAt(i) == '-') {
                sign = -1;
            }
            i++;
        }

        // 3. Convert digits
        while (i < n && Character.isDigit(s.charAt(i))) {
            result = result * 10 + (s.charAt(i) - '0');

            // 4. Handle overflow
            if (sign * result > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }

            if (sign * result < Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }

            i++;
        }

        return (int) (sign * result);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.myAtoi("42"));          // 42
        System.out.println(solution.myAtoi("   -42"));      // -42
        System.out.println(solution.myAtoi("4193 words"));  // 4193
        System.out.println(solution.myAtoi("words 4193"));  // 0
        System.out.println(solution.myAtoi("-91283472332")); // -2147483648
    }
}