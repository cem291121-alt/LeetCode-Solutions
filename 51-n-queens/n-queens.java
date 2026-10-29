import java.util.*;

class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> solutions = new ArrayList<>();
        int[] queenColumn = new int[n];
        Arrays.fill(queenColumn, -1);

        backtrack(0, n, queenColumn, solutions);
        return solutions;
    }

    private void backtrack(
            int row, int n, int[] queenColumn, List<List<String>> solutions) {
        if (row == n) {
            List<String> board = new ArrayList<>();

            for (int r = 0; r < n; r++) {
                char[] line = new char[n];
                Arrays.fill(line, '.');
                line[queenColumn[r]] = 'Q';
                board.add(new String(line));
            }

            solutions.add(board);
            return;
        }

        for (int col = 0; col < n; col++) {
            if (isSafe(row, col, queenColumn)) {
                queenColumn[row] = col;
                backtrack(row + 1, n, queenColumn, solutions);
                queenColumn[row] = -1;
            }
        }
    }

    private boolean isSafe(int row, int col, int[] queenColumn) {
        for (int previousRow = 0; previousRow < row; previousRow++) {
            int previousCol = queenColumn[previousRow];

            if (previousCol == col
                    || Math.abs(previousCol - col) == row - previousRow) {
                return false;
            }
        }

        return true;
    }
}