class Solution {
    public int numDecodings(String s) {
        if (s == null || s.isEmpty() || s.charAt(0) == '0') {
            return 0;
        }

        int previousTwo = 1; // Ways before the previous character
        int previousOne = 1; // Ways through the previous character

        for (int i = 1; i < s.length(); i++) {
            int current = 0;

            if (s.charAt(i) != '0') {
                current += previousOne;
            }

            int twoDigitNumber =
                    (s.charAt(i - 1) - '0') * 10 + (s.charAt(i) - '0');

            if (twoDigitNumber >= 10 && twoDigitNumber <= 26) {
                current += previousTwo;
            }

            previousTwo = previousOne;
            previousOne = current;
        }

        return previousOne;
    }
}