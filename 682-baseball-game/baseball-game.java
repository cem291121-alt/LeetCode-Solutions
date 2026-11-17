import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int calPoints(String[] operations) {
        Deque<Integer> scores = new ArrayDeque<>();

        for (String op : operations) {
            switch (op) {
                case "+":
                    int last = scores.pop();
                    int previous = scores.peek();
                    scores.push(last); // restore the last score
                    scores.push(last + previous);
                    break;

                case "D":
                    scores.push(scores.peek() * 2);
                    break;

                case "C":
                    scores.pop();
                    break;

                default:
                    scores.push(Integer.parseInt(op));
            }
        }

        int total = 0;
        for (int score : scores) {
            total += score;
        }
        return total;
    }
}