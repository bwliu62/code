package DFS2;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Get all valid permutations of l pairs of (), m pairs of <> and n pairs of {}.
 *
 * Assumptions
 *
 * l, m, n >= 0
 * l + m + n > 0
 * Examples
 *
 * l = 1, m = 1, n = 0, all the valid permutations are ["()<>", "(<>)", "<()>", "<>()"]
 * */

public class AllValidPermutationsOfParenthesesII {
    private static final char[] PS = {'(', ')', '<', '>', '{', '}'};

    public List<String> validParentheses(int l, int m, int n) {
        // Write your solution here
        List<String> res = new ArrayList<>();
        int[] remain = {l, l, m, m, n, n,};
        int targetLen = 2 * l + 2 * m + 2 * n;
        StringBuilder cur = new StringBuilder();
        Deque<Character> stack = new LinkedList<>(); //用stack回头看，记录有多少个左括号
        helper(cur, stack, remain, targetLen, res);
        return res;
    }

    private void helper(StringBuilder cur, Deque<Character> stack, int[] remain, int targetLen, List<String> res) {
        if (cur.length() == targetLen) {
            res.add(cur.toString());
            return;
        }
        for (int i = 0; i < remain.length; i++) {
            if (i % 2 == 0) { // 左括号
                if (remain[i] > 0) { // 如果还有左括号，就可以放
                    cur.append(PS[i]);
                    stack.offerFirst(PS[i]);
                    remain[i]--;
                    helper(cur, stack, remain, targetLen, res);
                    cur.deleteCharAt(cur.length() - 1);
                    stack.pollFirst();
                    remain[i]++;
                }
            } else { // 右括号
                if (!stack.isEmpty() && stack.peekFirst() == PS[i - 1]) { // 如果stack里还有左括号，且右括号和前一个左括号是同一类的话
                    cur.append(PS[i]); // 放进去配对
                    stack.pollFirst();
                    remain[i]--;
                    helper(cur, stack, remain, targetLen, res);
                    cur.deleteCharAt(cur.length() - 1);
                    stack.offerFirst(PS[i - 1]);
                    remain[i]++;
                }
            }
        }
    }
}
