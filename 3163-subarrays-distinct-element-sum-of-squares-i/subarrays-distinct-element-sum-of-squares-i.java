import java.util.*;

class Solution {
    public int sumCounts(List<Integer> nums) {
        int n = nums.size();
        int answer = 0;

        for (int left = 0; left < n; left++) {
            HashSet<Integer> distinct = new HashSet<>();

            for (int right = left; right < n; right++) {
                distinct.add(nums.get(right));

                answer += distinct.size() * distinct.size();
            }
        }

        return answer;
    }
}