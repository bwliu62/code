package BFS;

import java.util.ArrayDeque;
import java.util.Queue;

public class CheckCompleteTree {
    public static void main(String[] args) {
        CheckCompleteTree s = new CheckCompleteTree();
        int[] level = {3, 2, 5};
        int[] inOrder = {2, 3, 5};
        TreeNode root = ConstructTree.constructBST(level, inOrder);
        boolean res = s.isCompleted(root);
        System.out.println(res);
    }

    public boolean isCompleted(TreeNode root) {
        if (root == null) {
            return true;
        }
        // Write your solution here
        Queue<TreeNode> queue = new ArrayDeque<>();

        boolean flag = false;

        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur.left == null) {
                flag = true;
            } else if (flag) { // cur.left != null && flag == true
                return false;
            } else { // cur.left != null && flag == false
                queue.offer(cur.left);
            }

            if (cur.right == null) {
                flag = true;
            } else if (flag) {
                return false;
            } else {
                queue.offer(cur.right);
            }

        }
        return true;
    }
}
