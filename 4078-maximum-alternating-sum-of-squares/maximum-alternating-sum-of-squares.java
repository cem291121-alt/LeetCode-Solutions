import java.util.Arrays;

class Solution {
    public long maxAlternatingSum(int[] nums) {
        long[] squares = new long[nums.length];

        for (int i = 0; i < nums.length; i++) {
            squares[i] = (long) nums[i] * nums[i];
        }

        Arrays.sort(squares);

        long score = 0;
        for (int i = 0; i < squares.length; i++) {
            score += (i < squares.length / 2)
                    ? -squares[i]
                    : squares[i];
        }

        return score;
    }
}