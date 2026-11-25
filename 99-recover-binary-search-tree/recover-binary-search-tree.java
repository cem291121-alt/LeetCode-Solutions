/**
 * Definition for a binary tree node.
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
    private TreeNode first;
    private TreeNode second;
    private TreeNode previous;

    public void recoverTree(TreeNode root) {
        first = null;
        second = null;
        previous = null;

        inorder(root);

        // The two misplaced values are stored in first and second.
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }

    private void inorder(TreeNode node) {
        if (node == null) return;

        inorder(node.left);

        if (previous != null && previous.val > node.val) {
            if (first == null) {
                first = previous;
            }
            second = node;
        }
        previous = node;

        inorder(node.right);
    }
}