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
    public ListNode partition(ListNode head, int x) {
        ListNode lessDummy = new ListNode(0);
        ListNode greaterOrEqualDummy = new ListNode(0);

        ListNode less = lessDummy;
        ListNode greaterOrEqual = greaterOrEqualDummy;

        while (head != null) {
            if (head.val < x) {
                less.next = head;
                less = less.next;
            } else {
                greaterOrEqual.next = head;
                greaterOrEqual = greaterOrEqual.next;
            }
            head = head.next;
        }

        // Terminate the second list to avoid retaining old links.
        greaterOrEqual.next = null;
        less.next = greaterOrEqualDummy.next;

        return lessDummy.next;
    }
}