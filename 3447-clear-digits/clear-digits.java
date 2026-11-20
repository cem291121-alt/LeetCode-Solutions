import java.util.*;

class Solution {
    public String clearDigits(String s) {

        Stack<Character> st = new Stack<>();

        for (int i = 0; i < s.length(); i++) {

            char ch = s.charAt(i);

            if (Character.isDigit(ch)) {
                st.pop();
            } 
            else {
                st.push(ch);
            }
        }

        String ans = "";

        for (char c : st) {
            ans = ans + c;
        }

        return ans;
    }
}