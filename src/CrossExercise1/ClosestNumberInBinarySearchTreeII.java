package CrossExercise1;

import java.util.*;

public class ClosestNumberInBinarySearchTreeII {
    public int[] closestKValues(TreeNode root, double target, int k) {
        // Write your solution here
        Deque<Integer> res = new ArrayDeque<>(k);
        if (root == null || k <= 0) {
            return new int[0];
        }
        helper(root, target, k, res);
        int size = res.size();
        int[] r = new int[size];
        for (int i = 0; i < size; i++) {
            r[i] = res.pollFirst();
        }
        return r;
    }

    private void helper(TreeNode root, double target, int k, Deque<Integer> res) {
        if (root == null) {
            return;
        }
        helper(root.left, target, k, res);
        if (res.size() < k) {
            res.offerLast(root.key);
        } else {
            if (Math.abs(root.key - target) < Math.abs(res.peekFirst() - target)) {
                res.pollFirst();
                res.offerLast(root.key);
            } else {
                return;
            }
        }
        helper(root.right, target, k, res);
    }
}
