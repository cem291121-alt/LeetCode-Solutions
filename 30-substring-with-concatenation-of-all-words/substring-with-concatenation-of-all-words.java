import java.util.*;

class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();

        if (s == null || words == null || words.length == 0) {
            return result;
        }

        int wordLength = words[0].length();
        if (wordLength == 0) {
            return result;
        }

        Map<String, Integer> required = new HashMap<>();
        for (String word : words) {
            if (word.length() != wordLength) {
                return result;
            }
            required.put(word, required.getOrDefault(word, 0) + 1);
        }

        int windowSize = wordLength * words.length;

        for (int offset = 0; offset < wordLength; offset++) {
            int left = offset;
            int matchedWords = 0;
            Map<String, Integer> seen = new HashMap<>();

            for (int right = offset; right + wordLength <= s.length(); right += wordLength) {
                String word = s.substring(right, right + wordLength);

                if (!required.containsKey(word)) {
                    seen.clear();
                    matchedWords = 0;
                    left = right + wordLength;
                    continue;
                }

                seen.put(word, seen.getOrDefault(word, 0) + 1);
                matchedWords++;

                while (seen.get(word) > required.get(word)) {
                    String leftWord = s.substring(left, left + wordLength);
                    seen.put(leftWord, seen.get(leftWord) - 1);
                    matchedWords--;
                    left += wordLength;
                }

                if (matchedWords == words.length) {
                    result.add(left);

                    String leftWord = s.substring(left, left + wordLength);
                    seen.put(leftWord, seen.get(leftWord) - 1);
                    matchedWords--;
                    left += wordLength;
                }
            }
        }

        return result;
    }
}