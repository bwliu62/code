package DFS;


import java.util.ArrayList;
import java.util.List;

public class ValidParentheses1 {
    public List<String> validParentheses(int k) {
        List<String> result = new ArrayList<>();
        // the final string contains 2k characters;
        char[] cur = new char[k * 2];
        helper(cur, k, k, 0, result);
        return result;
    }
    // left : how man ' ( ' we still have
    // right : how many ' ) ' we still have
    // index : the current position in cur we want to fill in with either '(' or ')'

    private void helper(char[] cur, int left, int right, int index, List<String> result) {
        // terminate condition: when we don't have any parenthesis left.
        if (left == 0 && right == 0) {
            result.add(new String(cur));
            return;
        }
        // when we add a  '(' ?  --> when ever there is some '(' we can still use
        if (left > 0) {
            cur[index] = '(';
            helper(cur, left - 1, right, index + 1, result);
            // NOTICE : it looks like we don't do anything when back tracking to the previous level.
            // the code is still correct because:
            // 1. we are setting the character at index and when back tracking, what we need is just
            // (1). remove the character at index and
            // (2). add a different character at index.
            // 2. only when we fill in all the position in cur, we have a complete solution.
            // the code itself actually already suffices the above two points and it already does the
            // correct removing operation when back tracking to the previous level.
        }

        // when we can add a ')' ?  ---> when there is more '(' than ')' used,
        // because each ')' should be associated with a previous '('.
        if (right > left) {
            cur[index] = ')';
            helper(cur, left, right - 1, index + 1, result);
        }
    }

    public static void main(String[] args) {
        ValidParentheses1 v = new ValidParentheses1();
        int n = 3;
        List<String> res = v.validParentheses(n);
        for (String i: res
             ) {
            System.out.println(i);
        }
//        res:
//        ((()))
//        (()())
//        (())()
//        ()(())
//        ()()()
    }
}
