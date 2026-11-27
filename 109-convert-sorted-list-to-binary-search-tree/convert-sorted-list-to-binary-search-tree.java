/**
 * Definition for singly-linked list.
 * LeetCode provides this class, so you usually should not submit it yourself.
 *
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) {
 *         this.val = val;
 *         this.next = next;
 *     }
 * }
 *
 * Definition for a binary tree node.
 * LeetCode provides this class, so you usually should not submit it yourself.
 *
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

class Solution {
    private ListNode current;

    public TreeNode sortedListToBST(ListNode head) {
        current = head;
        int length = 0;

        for (ListNode node = head; node != null; node = node.next) {
            length++;
        }

        return buildTree(0, length - 1);
    }

    private TreeNode buildTree(int left, int right) {
        if (left > right) {
            return null;
        }

        int middle = left + (right - left) / 2;

        TreeNode leftChild = buildTree(left, middle - 1);

        TreeNode root = new TreeNode(current.val);
        current = current.next;

        root.left = leftChild;
        root.right = buildTree(middle + 1, right);

        return root;
    }
}