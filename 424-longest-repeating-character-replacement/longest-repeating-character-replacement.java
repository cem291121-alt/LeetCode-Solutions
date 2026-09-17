public class Solution {

    public int characterReplacement(String s, int k) {
        int[] frequency = new int[26];

        int left = 0;
        int maxFrequency = 0;
        int maxLength = 0;

        for (int right = 0; right < s.length(); right++) {
            int index = s.charAt(right) - 'A';
            frequency[index]++;

            maxFrequency = Math.max(maxFrequency, frequency[index]);

            // Characters to replace = window length - most frequent character count
            while ((right - left + 1) - maxFrequency > k) {
                frequency[s.charAt(left) - 'A']--;
                left++;
            }

            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.characterReplacement("ABAB", 2));   // 4
        System.out.println(solution.characterReplacement("AABABBA", 1)); // 4
    }
}