class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int[] dp = new int[budget + 1];

        for (int i = 0; i < n; i++) {
            int price = items[i][1];
            int firstCopyValue = 0;

            for (int j = 0; j < n; j++) {
                if (items[j][0] % items[i][0] == 0) {
                    firstCopyValue++;
                }
            }

            int[] previous = dp;
            int[] next = dp.clone();

            for (int spent = price; spent <= budget; spent++) {
                // First copy includes the free copies it triggers.
                next[spent] = Math.max(
                    next[spent],
                    previous[spent - price] + firstCopyValue
                );

                // Each additional copy contributes one.
                next[spent] = Math.max(
                    next[spent],
                    next[spent - price] + 1
                );
            }

            dp = next;
        }

        return dp[budget];
    }
}