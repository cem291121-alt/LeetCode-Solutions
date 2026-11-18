import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] answer = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();

        java.util.Arrays.fill(answer, -1);

        for (int i = 0; i < 2 * n; i++) {
            int currentIndex = i % n;

            while (!stack.isEmpty()
                    && nums[stack.peek()] < nums[currentIndex]) {
                answer[stack.pop()] = nums[currentIndex];
            }
            if (i < n) {
                stack.push(currentIndex);
            }
        }

        return answer;
    }
}