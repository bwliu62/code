package FinalExam;
import java.util.*;
import com.sun.source.tree.Tree;

/**
 * In a binary tree, two nodes are cousins of each other if they are at the same level and have different parents.
 *
 *
 *
 * For example, in the following tree:
 *
 *
 *
 *                      6
 *
 *                   /     \
 *
 *                3            5
 *
 *             /     \      /     \
 *
 *            7       8    1       2
 *
 *
 * 7 and 1 are cousins.
 *
 * 3 and 5 are not cousins.
 *
 * 7 and 5 are not cousins.
 *
 *
 *
 * Given a binary tree and two nodes, determine if the two nodes are cousins or not.
 * */
class TreeNode {
    public int key;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int key) {
        this.key = key;
    }
}
public class CousinsinaBinaryTree {
    public boolean isCousin(TreeNode root, TreeNode a, TreeNode b) {
        if (root == null) {
            return false;
        }
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        while(!q.isEmpty()) {
            int size = q.size();
            int found = 0;
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                // node a and b are under same parent
                if (cur.left != null && cur.right != null && (cur.left == a || cur.left == b) && (cur.right == a || cur.right == b)) {
                    return false;
                }
                if (cur.left != null) {
                    if (cur.left == a || cur.left == b) found++;
                    q.offer(cur.left);
                }
                if (cur.right != null) {
                    q.offer(cur.right);
                    if (cur.right == a || cur.right == b) found++;
                }
            }
            if (found == 2) {
                return true;
            }
        }
        return false;
    }

    // method 2 LCA solution
    public boolean isCousin2(TreeNode root, TreeNode a, TreeNode b) {
        if (root == null || root == a || root == b) {
            return false;
        }

        boolean[] res = {false};
        dfs(root, a, b, 0, res);
        return res[0];
    }

    private int dfs(TreeNode root, TreeNode a, TreeNode b, int level, boolean[] res) {
        if (root == null) {
            return -1;
        }
        if (root == a || root == b) {
            return level;
        }

        int left = dfs(root.left, a, b, level + 1, res);
        int right = dfs(root.right, a, b, level + 1, res);

        if (left == right && left - level > 1 ) {
            res[0] = true;
        }
        return left == -1 ? right : left;
    }
}
