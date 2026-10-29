class Solution {
    public int totalNQueens(int n) {
        return backtrack(n, 0, 0L, 0L, 0L);
    }

    private int backtrack(int n, int row, long columns,
                          long diagonals1, long diagonals2) {
        if (row == n) {
            return 1;
        }

        long available = ((1L << n) - 1) & ~(columns | diagonals1 | diagonals2);
        int count = 0;

        while (available != 0) {
            long position = available & -available; // Pick the rightmost available column.
            available -= position;

            count += backtrack(
                n,
                row + 1,
                columns | position,
                (diagonals1 | position) << 1,
                (diagonals2 | position) >> 1
            );
        }

        return count;
    }
}