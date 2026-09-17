import java.util.HashMap;
import java.util.Map;

public class Solution {

    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int INF = Integer.MAX_VALUE / 2;

        // bestUntil[i] = minimum length of a target-sum subarray
        // that ends at or before index i
        int[] bestUntil = new int[n];

        Map<Integer, Integer> prefixIndex = new HashMap<>();
        prefixIndex.put(0, -1);

        int prefixSum = 0;
        int answer = INF;

        for (int i = 0; i < n; i++) {
            prefixSum += arr[i];

            int currentLength = INF;
            int start = -1;

            // A subarray from start to i has sum == target
            if (prefixIndex.containsKey(prefixSum - target)) {
                start = prefixIndex.get(prefixSum - target) + 1;
                currentLength = i - start + 1;

                // Find the best target-sum subarray ending before `start`
                if (start > 0 && bestUntil[start - 1] != INF) {
                    answer = Math.min(answer, currentLength + bestUntil[start - 1]);
                }
            }

            int previousBest = (i > 0) ? bestUntil[i - 1] : INF;
            bestUntil[i] = Math.min(previousBest, currentLength);

            // Store latest index for this prefix sum
            prefixIndex.put(prefixSum, i);
        }

        return answer == INF ? -1 : answer;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] arr = {3, 2, 2, 4, 3};
        int target = 3;

        System.out.println(solution.minSumOfLengths(arr, target)); // 2
    }
}