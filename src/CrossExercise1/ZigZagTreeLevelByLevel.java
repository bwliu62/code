package CrossExercise1;
/**
 * Get the list of keys in a given binary tree layer by layer in zig-zag order.
 * <p>
 * Examples
 * <p>
 * 5
 * <p>
 * /    \
 * <p>
 * 3        8
 * <p>
 * /   \        \
 * <p>
 * 1     4        11
 * <p>
 * the result is [5, 3, 8, 11, 4, 1]
 */

import java.util.*;

class TreeNode {
    public int key;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int key) {
        this.key = key;
    }
}

public class ZigZagTreeLevelByLevel {

    public List<Integer> zigZag(TreeNode root) {
        // Write your solution here
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> deque = new ArrayDeque<>();

        deque.offerLast(root);
        int level = 0;
        while (!deque.isEmpty()) {
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                if (level % 2 == 0) { // 偶数行，每个node从右边出去，且每个node的孩子：先压右孩子，再压左孩子，从deque的左边进去
                    TreeNode node = deque.pollLast();
                    if (node.right != null) {
                        deque.offerFirst(node.right);
                    }
                    if (node.left != null) {
                        deque.offerFirst(node.left);
                    }

                    res.add(node.key);
                } else { // 奇数行，每个node从左边出去，且每个node的孩子： 先压左孩子，再压右孩子，从deque的右边进去
                    TreeNode node = deque.pollFirst();
                    if (node.left != null) {
                        deque.offerLast(node.left);
                    }
                    if (node.right != null) {
                        deque.offerLast(node.right);
                    }
                    res.add(node.key);
                }

            }
            level = 1 - level; // instead of level++, we only use this way to avoid stack over flow.
        }
        return res;
    }

    public static TreeNode constructBST(int[] level, int[] in) {
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < in.length; i++) {
            inMap.put(in[i], i);
        }
        List<Integer> lList = new ArrayList<>();
        for (int num : level) {
            lList.add(num);
        }
        return helper(lList, inMap);
    }

    private static TreeNode helper(List<Integer> level, Map<Integer, Integer> inMap) {
        if (level.isEmpty()) return null;

        TreeNode root = new TreeNode(level.remove(0));
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        for (int num : level) {
            if (inMap.get(num) < inMap.get(root.key)) {
                left.add(num);
            } else {
                right.add(num);
            }
        }
        root.left = helper(left, inMap);
        root.right = helper(right, inMap);
        return root;

    }

    public static void main(String args[]) {
        int[] level = {5, 3, 8, 1, 4, 11};
        int[] inOrder = {1, 3, 4, 5, 8, 11};
        int[] level1 = {5, 2, 10, 1, 3, 8, 13, 4, 11, 15};
        int[] inOrder1 = {1, 2, 3, 4, 5, 8, 10, 11, 13, 15};
        TreeNode root = constructBST(level1, inOrder1);

        ZigZagTreeLevelByLevel z = new ZigZagTreeLevelByLevel();
        List<Integer> res = z.zigZag(root);
        for (int i : res
        ) {
            System.out.print(i + " ");
        }
    }
}
