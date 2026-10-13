class Solution {
    public void solveSudoku(char[][] board) {
        solve(board);
    }

    private boolean solve(char[][] board) {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (board[r][c] != '.') continue;

                for (char digit = '1'; digit <= '9'; digit++) {
                    if (isValid(board, r, c, digit)) {
                        board[r][c] = digit;

                        if (solve(board)) return true;

                        board[r][c] = '.';
                    }
                }

                return false; // No digit fits this cell.
            }
        }

        return true; // No empty cells remain.
    }

    private boolean isValid(char[][] board, int row, int col, char digit) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == digit || board[i][col] == digit) {
                return false;
            }

            int boxRow = 3 * (row / 3) + i / 3;
            int boxCol = 3 * (col / 3) + i % 3;
            if (board[boxRow][boxCol] == digit) {
                return false;
            }
        }

        return true;
    }
}