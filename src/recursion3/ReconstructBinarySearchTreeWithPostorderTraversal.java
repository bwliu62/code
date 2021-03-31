package recursion3;

import com.sun.source.tree.Tree;

public class ReconstructBinarySearchTreeWithPostorderTraversal {
    public TreeNode reconstruct(int[] post) {
        int[] index = new int[] {post.length - 1};
        return helper(post, index, Integer.MIN_VALUE);
    }

    private TreeNode helper(int[] post, int[] index, int min) {
        // Since it's a BST,
        // the "min" is actually the root,
        // and we are using the root value to determine the boundary of left/right subtree.
        if (index[0] < 0  || post[index[0]] <= min) {
            return null;
        }
        TreeNode root = new TreeNode(post[index[0]--]);
        root.right = helper(post, index, root.key);
        root.left = helper(post, index, min);
        return root;
    }
}
