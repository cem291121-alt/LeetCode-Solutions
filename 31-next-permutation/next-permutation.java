class Solution {
    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;

        // Find the first number from the right that is smaller
        // than the number after it
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        // Find the smallest number on the right that is bigger than nums[i]
        if (i >= 0) {
            int j = nums.length - 1;
            while (nums[j] <= nums[i]) {
                j--;
            }

            swap(nums, i, j);
        }

        // Reverse the numbers after i
        int left = i + 1;
        int right = nums.length - 1;

        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}