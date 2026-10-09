class Solution {
    public int minInsertions(String s) {
        int insertions = 0;
        int need = 0; // Number of ')' characters still needed

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '(') {
                // An unfinished ')' pair means we need to insert one more ')'
                if (need % 2 == 1) {
                    insertions++;
                    need--;
                }
                need += 2;
            } else {
                need--;

                // No opening '(' was available for this ')'
                if (need < 0) {
                    insertions++; // Insert an '(' before this ')'
                    need = 1;      // One more ')' is needed to complete its pair
                }
            }
        }

        return insertions + need;
    }
}