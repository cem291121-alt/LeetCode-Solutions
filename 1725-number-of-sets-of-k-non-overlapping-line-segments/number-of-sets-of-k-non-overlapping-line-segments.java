class Solution {
    static final int MOD = 1_000_000_007;

    public int numberOfSets(int n, int k) {
        long[][] dp = new long[n][k + 1];
        long[][] prefix = new long[n][k + 1];

        // Choosing 0 segments is always one valid way.
        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
            prefix[i][0] = i + 1;
        }

        for (int segments = 1; segments <= k; segments++) {
            for (int point = 1; point < n; point++) {
                
                dp[point][segments] = dp[point - 1][segments];
                dp[point][segments] += prefix[point - 1][segments - 1];
                dp[point][segments] %= MOD;

                prefix[point][segments] =
                    (prefix[point - 1][segments] + dp[point][segments]) % MOD;
            }
        }

        return (int) dp[n - 1][k];
    }
}