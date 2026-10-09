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
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k <= 1) {
            return head;
        }

        // Check whether there are k nodes to reverse
        ListNode check = head;
        for (int i = 0; i < k; i++) {
            if (check == null) {
                return head; // Fewer than k nodes remain
            }
            check = check.next;
        }

        // Reverse these k nodes
        ListNode previous = null;
        ListNode current = head;

        for (int i = 0; i < k; i++) {
            ListNode nextNode = current.next;
            current.next = previous;
            previous = current;
            current = nextNode;
        }

        // Connect this group to the reversed rest
        head.next = reverseKGroup(current, k);

        return previous;
    }
}