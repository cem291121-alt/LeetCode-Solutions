class Solution {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;

        // Place each value x in position x - 1 when possible.
        for (int i = 0; i < n; i++) {
            while (nums[i] > 0
                    && nums[i] <= n
                    && nums[nums[i] - 1] != nums[i]) {
                int correctIndex = nums[i] - 1;
                int temp = nums[i];
                nums[i] = nums[correctIndex];
                nums[correctIndex] = temp;
            }
        }

        // The first position that does not contain its expected value
        // identifies the smallest missing positive.
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        return n + 1;
    }
}