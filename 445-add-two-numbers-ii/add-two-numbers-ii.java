/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Deque<Integer> first = new ArrayDeque<>();
        Deque<Integer> second = new ArrayDeque<>();

        while (l1 != null) {
            first.push(l1.val);
            l1 = l1.next;
        }

        while (l2 != null) {
            second.push(l2.val);
            l2 = l2.next;
        }

        int carry = 0;
        ListNode result = null;

        while (!first.isEmpty() || !second.isEmpty() || carry != 0) {
            int sum = carry;

            if (!first.isEmpty()) sum += first.pop();
            if (!second.isEmpty()) sum += second.pop();

            ListNode node = new ListNode(sum % 10);
            node.next = result;
            result = node;
            carry = sum / 10;
        }

        return result;
    }
}