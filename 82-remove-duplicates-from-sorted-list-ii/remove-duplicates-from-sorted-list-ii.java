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
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0, head);
        ListNode previous = dummy;
        ListNode current = head;

        while (current != null) {
            if (current.next != null && current.val == current.next.val) {
                int duplicateValue = current.val;

                while (current != null && current.val == duplicateValue) {
                    current = current.next;
                }

                previous.next = current;
            } else {
                previous = current;
                current = current.next;
            }
        }

        return dummy.next;
    }
}