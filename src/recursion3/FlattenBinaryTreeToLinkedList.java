package recursion3;

/**
 * Given a binary tree, flatten it to a linked list in-place.
 *
 * For example,
 * Given
 *
 *          1
 *         / \
 *        2   5
 *       / \   \
 *      3   4   6
 * The flattened tree should look like:
 *
 *    1
 *     \
 *      2
 *       \
 *        3
 *         \
 *          4
 *           \
 *            5
 *             \
 *              6
 * */
public class FlattenBinaryTreeToLinkedList {
    public TreeNode flatten(TreeNode root) {
        // Write your solution here

        TreeNode[] prev = new TreeNode[1];
        helper(root, prev);
        return root;
    }

    private void helper(TreeNode root, TreeNode[] prev) {
        if (root == null) {
            return;
        }

        TreeNode left = root.left; // we record left node in advance, because it will be changed.
        TreeNode right = root.right;

        if (prev[0] != null) { //if :  root is the first node to visit
            prev[0].right = root;
        }
        // here we set left node to null, so we need to record root.left in advance.
        root.left = null;  // OR root.left = prev[0] if we need to make it a doubly linked list
        prev[0] = root;

        helper(left, prev);
        helper(right, prev);
    }
}
