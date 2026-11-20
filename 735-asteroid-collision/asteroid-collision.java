import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> stack = new ArrayDeque<>();

        for (int asteroid : asteroids) {
            boolean destroyed = false;

            while (!stack.isEmpty()
                    && stack.peekLast() > 0
                    && asteroid < 0) {
                int top = stack.peekLast();

                if (top < -asteroid) {
                    stack.removeLast(); 
                } else if (top == -asteroid) {
                    stack.removeLast(); 
                    destroyed = true;
                    break;
                } else {
                    destroyed = true; 
                    break;
                }
            }

            if (!destroyed) {
                stack.addLast(asteroid);
            }
        }

        int[] result = new int[stack.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = stack.removeFirst();
        }

        return result;
    }
}