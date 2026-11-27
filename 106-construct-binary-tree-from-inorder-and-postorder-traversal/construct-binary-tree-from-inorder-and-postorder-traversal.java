import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode provides the TreeNode class.
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
    private int postorderIndex;
    private Map<Integer, Integer> inorderPositions;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        postorderIndex = postorder.length - 1;
        inorderPositions = new HashMap<>();

        for (int i = 0; i < inorder.length; i++) {
            inorderPositions.put(inorder[i], i);
        }

        return build(inorder, postorder, 0, inorder.length - 1);
    }

    private TreeNode build(
        int[] inorder,
        int[] postorder,
        int left,
        int right
    ) {
        if (left > right) {
            return null;
        }

        int value = postorder[postorderIndex--];
        TreeNode root = new TreeNode(value);
        int middle = inorderPositions.get(value);

        // Build right subtree first because postorder is
        // left subtree, right subtree, root.
        root.right = build(inorder, postorder, middle + 1, right);
        root.left = build(inorder, postorder, left, middle - 1);

        return root;
    }
}