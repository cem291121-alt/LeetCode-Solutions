class Solution {
    public int minOperations(int[] nums, int x) {
        int total = 0;
        for (int num : nums) {
            total += num;
        }

        // Removing from both ends is equivalent to keeping a middle subarray
        // whose sum is total - x. Find the longest such subarray.
        int target = total - x;
        if (target == 0) {
            return nums.length;
        }

        int left = 0;
        int windowSum = 0;
        int longest = -1;

        for (int right = 0; right < nums.length; right++) {
            windowSum += nums[right];

            while (left <= right && windowSum > target) {
                windowSum -= nums[left++];
            }

            if (windowSum == target) {
                longest = Math.max(longest, right - left + 1);
            }
        }

        return longest == -1 ? -1 : nums.length - longest;
    }
}