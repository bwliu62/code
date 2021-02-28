package CrossExercise1;

import java.util.*;

/**
 * Given two nodes in a K-nary tree, find their lowest common ancestor.
 *
 * Assumptions
 *
 * -There is no parent pointer for the nodes in the K-nary tree.
 *
 * -The given two nodes are guaranteed to be in the K-nary tree.
 *
 * Examples
 *          5
 *
 *        /   \
 *
 *       9     12
 *
 *    / | \      \
 *
 *   1  2  3      14
 *
 * The lowest common ancestor of 2 and 14 is 5.
 *
 * The lowest common ancestor of 2 and 9 is 9.
 *
 * high level idea :
 * base case :
 * if (root == null || root == a || root == b){
 * return root
 * }
 * <p>
 * recursive rule:
 * r0,r1,r2,...,r(k-1)
 * case 1: if all of them are null, return null
 * case 2: only one of them are not null, return the non-null node;
 * case 3: more than one of them are not null, return root;
 */
class KnaryTreeNode {
    int key;
    List<KnaryTreeNode> children;

    public KnaryTreeNode(int key) {
        this.key = key;
        this.children = new ArrayList<>();
    }
}

public class LCAWithKnarryTree {
    public KnaryTreeNode lowestCommonAncestor(KnaryTreeNode root, KnaryTreeNode a, KnaryTreeNode b) {
        // Write your solution here
        if (root == null || root == a || root == b) {
            return root;
        }

        KnaryTreeNode found = null;
        for (KnaryTreeNode i : root.children) {
            KnaryTreeNode node = lowestCommonAncestor(i, a, b);

            if (node == null) {
                continue;
            }
            if (found == null) { // 进入这个if说明至少找到了1个node a or b
                found = node;
            } else {            // step 3： 如果found ！= null， 又进入这个else ， 说明找到两个node，return root
                return root;
            }
        }
        return found;       // step 2
    }
}
