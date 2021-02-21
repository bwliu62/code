package recursion2;

/*
Given a binary tree in which each node contains an int number.

Find the maximum possible sum from any leaf node to another leaf node.

The maximum sum path may or may not go through root.

Expected time complexity is O(n).
* **/
public class MaxSumPathBST {
    public int maxPathSum(TreeNode root) {
        int[] maxSum = {Integer.MIN_VALUE};
        maxGain(root, maxSum);
        return maxSum[0];
    }

    private int maxGain(TreeNode node, int[] maxSum) {
        if (node == null) {
            return 0; //
        }
        if (node.left == null && node.right == null) {
            return node.key;
        }

        // step 1
        int leftGain = maxGain(node.left, maxSum);
        int rightGain = maxGain(node.right, maxSum);

        if (node.left != null && node.right != null) { // step 2.
            int newPathGain = node.key + leftGain + rightGain;
            maxSum[0] = Math.max(maxSum[0], newPathGain);
            return node.key + Math.max(leftGain, rightGain);
        }

        // if there is one null child, we cannot make a new path.
        // step 3
        return node.left == null ? rightGain + node.key : leftGain + node.key;
    }

}
