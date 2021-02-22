package recursion2;

/*
Given a binary tree in which each node contains an int number.

Find the maximum possible sum from any leaf node to another leaf node.

The maximum sum path may or may not go through root.

Expected time complexity is O(n).

三部曲：
1. what do you want from your left child and right child?
    left : maximum "root to leaf" path sum of left subtree
    right : maximum "root to leaf" path sum of right subtree
2. what should you do in current layer?
    1)calculate left + right + root.val
    2)update global max if possible
3. what do you report to  your parent?
    maximum path sum from root to leaf
    i.e. return max(left, right) + root.val  (be careful about the case where one side child = null)
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
        if (node.left == null && node.right == null) { // 到叶子节点了
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
