class Solution {
    public String countAndSay(int n) {
        String term = "1";

        for (int i = 2; i <= n; i++) {
            StringBuilder next = new StringBuilder();

            for (int j = 0; j < term.length();) {
                int k = j;
                while (k < term.length() && term.charAt(k) == term.charAt(j)) {
                    k++;
                }

                next.append(k - j).append(term.charAt(j));
                j = k;
            }

            term = next.toString();
        }

        return term;
    }
}