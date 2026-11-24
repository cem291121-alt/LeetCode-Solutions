class Solution {
    public int maxLength(int[] nums) {
        int n = nums.length;
        int best = 1;

        for (int left = 0; left < n; left++) {
            long product = 1;
            long gcd = 0;
            long lcm = 1;

            for (int right = left; right < n; right++) {
                long x = nums[right];
                product *= x;

                gcd = gcd(gcd, x);
                lcm = lcm / gcd(lcm, x) * x;

                if (product == lcm * gcd) {
                    best = Math.max(best, right - left + 1);
                }
            }
        }

        return best;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long t = a % b;
            a = b;
            b = t;
        }
        return a;
    }
}