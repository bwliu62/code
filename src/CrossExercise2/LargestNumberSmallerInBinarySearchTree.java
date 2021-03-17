package CrossExercise2;

public class LargestNumberSmallerInBinarySearchTree {
    public int largestSmaller(TreeNode root, int target) {
        // Write your solution here
        int res = Integer.MIN_VALUE;
        while (root != null) {
            if (root.key >= target) {
                root = root.left;
            } else {
                res = root.key;
                root = root.right;
            }
        }
        return res;
    }
}
