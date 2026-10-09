import java.util.Stack;

class Solution {
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1); // Starting index before the string

        int longest = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();

                if (stack.isEmpty()) {
                    // This ')' has no matching '('
                    stack.push(i);
                } else {
                    int length = i - stack.peek();
                    longest = Math.max(longest, length);
                }
            }
        }

        return longest;
    }
}