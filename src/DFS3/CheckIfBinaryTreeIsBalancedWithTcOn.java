package DFS3;

class TreeNode {
    public int key;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int key) {
        this.key = key;
    }
}

public class CheckIfBinaryTreeIsBalancedWithTcOn {
    public boolean isBalanced(TreeNode root) {
        // Write your solution here
        // Method 1:
        // if(root == null){
        //   return true;
        // }
        // boolean left = isBalanced(root.left);
        // boolean right = isBalanced(root.right);

        // if(!left||!right){
        //   return false;
        // }
        // int leftHeight = getHeight(root.left);
        // int rightHeight = getHeight(root.right);
        // if(Math.abs(leftHeight - rightHeight)>1){
        // return false;
        // }
        // return true;

        // Method 2: O(n) time complexity
        if (root == null) {
            return true;
        }
        int res = height(root);
        return res != -1;
    }

    private int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);
        int cur = Math.max(leftHeight, rightHeight) + 1;
        return cur;
    }

    private int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = height(root.left);
        int right = height(root.right);

        if (left == -1 || right == -1 || Math.abs(left - right) > 1) {
            return -1;
        }
        return Math.max(left, right) + 1;
    }
}
