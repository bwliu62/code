package CrossExercise1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The given two nodes are not guaranteed to be in the binary tree
 *
 * Examples
 *
 *          5
 *
 *        /   \
 *
 *      9     12
 *
 *    /  \      \
 *
 *  2    3      14
 *
 * The lowest common ancestor of 2 and 14 is 5
 *
 * The lowest common ancestor of 2 and 9 is 9
 *
 * The lowest common ancestor of 2 and 8 is null (8 is not in the tree)
 */
class TreeNodeP {
    public int key;
    public TreeNodeP left;
    public TreeNodeP right;
    public TreeNodeP parent;

    public TreeNodeP(int key, TreeNodeP parent) {
        this.key = key;
        this.parent = parent;
    }
}

/**
 * 思路： 1： 分别计算两个node在树中有多高，取他们的高度差。
 * 2：  把更深的node.parent 拉到和矮node 一个layer上， 同时向上找。
 */
public class LCAWithParentNode {
    public TreeNodeP lowestCommonAncestor(TreeNodeP one, TreeNodeP two) {
        // Write your solution here.
        int h1 = height(one); // 分别找两个node的高度
        int h2 = height(two);

        if (h1 < h2) { // 取差值
            return helper(two, one, h2 - h1);
        } else {
            return helper(one, two, h1 - h2);
        }

    }

    private TreeNodeP helper(TreeNodeP longer, TreeNodeP shorter, int diff) {
        while (diff > 0) { // put them on the same level
            longer = longer.parent;
            diff--;
        }

        while (longer != shorter) { // 同时向上找
            longer = longer.parent;
            shorter = shorter.parent;
        }

        return longer;

    }

    private int height(TreeNodeP node) {
        int height = 0;
        while (node != null) {
            node = node.parent;
            height++;
        }
        return height;
    }


}
