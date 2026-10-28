import java.util.HashSet;
import java.util.Set;

class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        @SuppressWarnings("unchecked")
        Set<Integer>[][] dp = new HashSet[m][n];

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                dp[r][c] = new HashSet<>();

                if (r == 0 && c == 0) {
                    dp[r][c].add(grid[r][c]);
                    continue;
                }

                if (r > 0) {
                    for (int xor : dp[r - 1][c]) {
                        dp[r][c].add(xor ^ grid[r][c]);
                    }
                }

                if (c > 0) {
                    for (int xor : dp[r][c - 1]) {
                        dp[r][c].add(xor ^ grid[r][c]);
                    }
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int xor : dp[m - 1][n - 1]) {
            answer = Math.min(answer, xor);
        }
        return answer;
    }
}