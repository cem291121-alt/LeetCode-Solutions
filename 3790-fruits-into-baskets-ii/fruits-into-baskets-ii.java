class Solution {
    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        
        int count = 0;

        for (int i = 0; i < fruits.length; i++) {
            
            boolean placed = false;

            for (int j = 0; j < baskets.length; j++) {
                
                if (baskets[j] >= fruits[i]) {
                    baskets[j] = -1;   // basket is used
                    placed = true;
                    break;
                }
            }

            if (!placed) {
                count++;
            }
        }

        return count;
    }
}