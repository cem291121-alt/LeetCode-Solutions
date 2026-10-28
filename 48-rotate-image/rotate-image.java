class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;

        // Transpose across the main diagonal.
        for (int r = 0; r < n; r++) {
            for (int c = r + 1; c < n; c++) {
                int temp = matrix[r][c];
                matrix[r][c] = matrix[c][r];
                matrix[c][r] = temp;
            }
        }

        // Reverse each row.
        for (int r = 0; r < n; r++) {
            for (int left = 0, right = n - 1; left < right; left++, right--) {
                int temp = matrix[r][left];
                matrix[r][left] = matrix[r][right];
                matrix[r][right] = temp;
            }
        }
    }
}