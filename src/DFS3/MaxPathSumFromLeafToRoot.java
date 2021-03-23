package DFS3;
/**
 * Given a binary tree in which each node contains an integer number. Find the maximum possible path sum from a leaf to root.
 *
 *
 *
 * Assumptions
 *
 * The root of given binary tree is not null.
 *
 *
 *
 * Examples
 *
 *
 *
 *          10
 *
 *        /      \
 *
 *     -2         7
 *
 *   /     \
 *
 * 8      -4
 *
 * The maximum path sum is 10 + 7 = 17.
 * */
public class MaxPathSumFromLeafToRoot {
    // Method 1 : Pass down the prefix sum.
    public int maxPathSum(TreeNode root) {
        // Assumption: root != null
        return maxPathSum(root, 0);
    }

    private int maxPathSum(TreeNode root, int sum) {
        sum += root.key;
        if (root.left == null && root.right == null) { // leaf node as base case
            return sum;
        }else if (root.left == null) {
            return maxPathSum(root.right, sum);
        } else if (root.right == null) {
            return maxPathSum(root.left, sum);
        }
        return Math.max(maxPathSum(root.left, sum), maxPathSum(root.right, sum));
    }

    // Method 2: Bottom up return the max suffix sum.
    public int maxPathSum2(TreeNode root) {
        // Assumptions : root != null
        if (root.left == null && root.right == null) {
            return root.key;
        }
        if (root.left == null) {
            return maxPathSum2(root.right) + root.key;
        }
        if (root.right == null) {
            return maxPathSum2(root.left) + root.key;
        }
        return Math.max(maxPathSum2(root.left) , maxPathSum2(root.right)) + root.key;
    }

}
