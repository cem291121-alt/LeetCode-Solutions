public class Solution {

    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1) {
            return 0;
        }

        int count = 0;
        int left = 0;
        long product = 1;

        for (int right = 0; right < nums.length; right++) {
            product *= nums[right];

            while (product >= k) {
                product /= nums[left];
                left++;
            }
            count += right - left + 1;
        }

        return count;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] nums = {10, 5, 2, 6};
        int k = 100;

        System.out.println(solution.numSubarrayProductLessThanK(nums, k)); 
    }
}