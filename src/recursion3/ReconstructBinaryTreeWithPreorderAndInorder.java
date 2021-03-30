package recursion3;

import com.sun.source.tree.Tree;

import java.util.HashMap;
import java.util.Map;
/**
 * Given the preorder and inorder traversal sequence of a binary tree, reconstruct the original tree.
 *
 * Assumptions
 *
 * The given sequences are not null and they have the same length
 * There are no duplicate keys in the binary tree
 * Examples
 *
 * preorder traversal = {5, 3, 1, 4, 8, 11}
 *
 * inorder traversal = {1, 3, 4, 5, 8, 11}
 *
 * the corresponding binary tree is
 *
 *         5
 *
 *       /    \
 *
 *     3        8
 *
 *   /   \        \
 *
 * 1      4        11
 *
 * How is the binary tree represented?
 *
 * We use the pre order traversal sequence with a special symbol "#" denoting the null node.
 *
 * For Example:
 *
 * The sequence [1, 2, #, 3, 4, #, #, #] represents the following binary tree:
 *
 *     1
 *
 *   /   \
 *
 *  2     3
 *
 *       /
 *
 *     4
 * */
public class ReconstructBinaryTreeWithPreorderAndInorder {
    // Method 1: Utilizing the inOrder sequence to determine the size of left/right subtrees.
    // TC = O(n) + O(n) = O(n)
    // SC = O(height) + O(n) = O(n)
    public TreeNode reconstruct(int[] in, int[] pre) {
        Map<Integer, Integer> inIndex = indexMap(in);
        return helper(pre, inIndex, 0, in.length - 1, 0, pre.length - 1);
    }

    private TreeNode helper(int[] pre, Map<Integer, Integer> inIndex, int inLeft, int inRight, int preLeft, int preRight) {
        if (inLeft > inRight) {
            return null;
        }
        TreeNode root = new TreeNode(pre[preLeft]);
        int inMid = inIndex.get(root.key);
        root.left = helper(pre, inIndex, inLeft, inMid - 1, preLeft + 1, preLeft + inMid - inLeft);
        root.right = helper(pre, inIndex, inMid + 1, inRight, preRight + inMid - inRight + 1, preRight);
        return root;
    }

    private Map<Integer, Integer> indexMap(int[] in) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < in.length; i++) {
            map.put(in[i], i);
        }
        return map;
    }

    // Method 2 : traversing and constructing the binary tree at the same time
    public TreeNode reconstruct2(int[] in, int[] pre) {
        int[] preIndex = new int[]{0};
        int[] inIndex = new int[]{0};
        return helper2(pre, in, preIndex, inIndex, Integer.MAX_VALUE);
    }

    private TreeNode helper2(int[] pre, int[] in, int[] preIndex, int[] inIndex, int target) {
        // Traversing and construct the binary tree using preOrder and inOrder,
        // the preOrder is [root][left subtree][right subtree]
        // from the preOrder, we know the root of the binary tree,
        // the inOrder is [left subtree][root][right subtree]
        // when we know the root, we actually know the boundary of the left/ right subtree.
        // The "target" is actually the root, and we are using inOrder to identify the boundary of left subtree.
        if (inIndex[0] >= in.length || in[inIndex[0]] == target) {
            return null;
        }
        TreeNode root = new TreeNode(pre[preIndex[0]]);
        // preOrder, advance the index by 1 since we already finish the root.
        preIndex[0]++;
        root.left = helper2(pre, in, preIndex, inIndex, root.key);
        inIndex[0]++;
        root.right = helper2(pre, in, preIndex, inIndex, target);
        return root;
    }
}
