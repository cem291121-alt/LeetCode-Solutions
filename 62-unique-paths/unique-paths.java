class Solution {
    public int uniquePaths(int m, int n) {
        long[] dp = new long[n];
        java.util.Arrays.fill(dp, 1);

        for (int row = 1; row < m; row++) {
            for (int col = 1; col < n; col++) {
                dp[col] += dp[col - 1];
            }
        }

        return (int) dp[n - 1];
    }
}