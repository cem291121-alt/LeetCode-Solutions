import java.util.HashMap;
import java.util.Map;

public class Solution {

    public int totalFruit(int[] fruits) {
        Map<Integer, Integer> basket = new HashMap<>();

        int left = 0;
        int maxFruits = 0;

        for (int right = 0; right < fruits.length; right++) {
            basket.put(fruits[right], basket.getOrDefault(fruits[right], 0) + 1);

            // Only two fruit types are allowed
            while (basket.size() > 2) {
                int leftFruit = fruits[left];
                basket.put(leftFruit, basket.get(leftFruit) - 1);

                if (basket.get(leftFruit) == 0) {
                    basket.remove(leftFruit);
                }

                left++;
            }

            maxFruits = Math.max(maxFruits, right - left + 1);
        }

        return maxFruits;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] fruits = {1, 2, 1};
        System.out.println(solution.totalFruit(fruits)); // 3
    }
}