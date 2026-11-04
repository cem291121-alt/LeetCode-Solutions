import java.util.*;

class Solution {
    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        if (k == 0) return 0;

        long[] costs = windowCosts(nums, x);
        final long INF = Long.MAX_VALUE / 4;

        // dp[j][i]: minimum cost to make j non-overlapping windows
        // using the first i elements.
        long[][] dp = new long[k + 1][n + 1];
        for (long[] row : dp) Arrays.fill(row, INF);
        Arrays.fill(dp[0], 0);

        for (int j = 1; j <= k; j++) {
            for (int i = 1; i <= n; i++) {
                dp[j][i] = dp[j][i - 1]; // Skip nums[i - 1]
                if (i >= x && dp[j - 1][i - x] != INF) {
                    dp[j][i] = Math.min(
                        dp[j][i],
                        dp[j - 1][i - x] + costs[i - x]
                    );
                }
            }
        }

        return dp[k][n];
    }

    // Returns the minimum equalizing cost for each length-x window.
    private long[] windowCosts(int[] nums, int x) {
        int count = nums.length - x + 1;
        long[] costs = new long[count];

        TreeMap<Integer, Integer> left = new TreeMap<>();  // lower half
        TreeMap<Integer, Integer> right = new TreeMap<>(); // upper half
        long leftSum = 0, rightSum = 0;
        int leftSize = 0, rightSize = 0;

        for (int i = 0; i < nums.length; i++) {
            int value = nums[i];

            if (left.isEmpty() || value <= left.lastKey()) {
                add(left, value);
                leftSize++;
                leftSum += value;
            } else {
                add(right, value);
                rightSize++;
                rightSum += value;
            }

            // Keep left with either the same number of elements as right,
            // or one more.
            while (leftSize > rightSize + 1) {
                int moved = left.lastKey();
                remove(left, moved);
                leftSize--;
                leftSum -= moved;
                add(right, moved);
                rightSize++;
                rightSum += moved;
            }
            while (leftSize < rightSize) {
                int moved = right.firstKey();
                remove(right, moved);
                rightSize--;
                rightSum -= moved;
                add(left, moved);
                leftSize++;
                leftSum += moved;
            }

            if (i >= x) {
                int outgoing = nums[i - x];
                if (left.containsKey(outgoing)) {
                    remove(left, outgoing);
                    leftSize--;
                    leftSum -= outgoing;
                } else {
                    remove(right, outgoing);
                    rightSize--;
                    rightSum -= outgoing;
                }

                while (leftSize > rightSize + 1) {
                    int moved = left.lastKey();
                    remove(left, moved);
                    leftSize--;
                    leftSum -= moved;
                    add(right, moved);
                    rightSize++;
                    rightSum += moved;
                }
                while (leftSize < rightSize) {
                    int moved = right.firstKey();
                    remove(right, moved);
                    rightSize--;
                    rightSum -= moved;
                    add(left, moved);
                    leftSize++;
                    leftSum += moved;
                }
            }

            if (i >= x - 1) {
                long median = left.lastKey();
                costs[i - x + 1] =
                    median * leftSize - leftSum +
                    rightSum - median * rightSize;
            }
        }

        return costs;
    }

    private void add(TreeMap<Integer, Integer> map, int value) {
        map.merge(value, 1, Integer::sum);
    }

    private void remove(TreeMap<Integer, Integer> map, int value) {
        int count = map.get(value);
        if (count == 1) map.remove(value);
        else map.put(value, count - 1);
    }
}