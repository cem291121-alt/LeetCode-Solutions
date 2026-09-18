import java.util.*;

class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] first = new int[26];
        int[] last = new int[26];

        Arrays.fill(first, n);

        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            first[c] = Math.min(first[c], i);
            last[c] = i;
        }

        List<String> result = new ArrayList<>();
        int previousEnd = -1;

        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';

            // Consider only the first occurrence of each character
            if (i != first[c]) continue;

            int end = getValidEnd(s, i, first, last);

            if (end == -1) continue;

            if (i > previousEnd) {
                result.add(s.substring(i, end + 1));
            } else {
                // Current substring is smaller and contained in the last one
                result.set(result.size() - 1, s.substring(i, end + 1));
            }

            previousEnd = end;
        }

        return result;
    }

    private int getValidEnd(String s, int start, int[] first, int[] last) {
        int end = last[s.charAt(start) - 'a'];

        for (int i = start; i <= end; i++) {
            int c = s.charAt(i) - 'a';

            // Character began before this substring: invalid interval
            if (first[c] < start) {
                return -1;
            }

            end = Math.max(end, last[c]);
        }

        return end;
    }
}