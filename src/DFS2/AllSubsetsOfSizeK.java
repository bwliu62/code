package DFS2;

import java.util.ArrayList;
import java.util.List;
/**
 * Given a set of characters represented by a String, return a list containing all subsets of the characters whose size is K.
 *
 * Assumptions
 *
 * There are no duplicate characters in the original set.
 *
 * Examples
 *
 * Set = "abc", K = 2, all the subsets are [“ab”, “ac”, “bc”].
 *
 * Set = "", K = 0, all the subsets are [""].
 *
 * Set = "", K = 1, all the subsets are [].
 * */
public class AllSubsetsOfSizeK {
    public List<String> subSetsOfSizeK(String set, int k) {
        // Write your solution here
        List<String> res = new ArrayList<>();
        if (set == null) {
            return res;
        }
        char[] arr = set.toCharArray();
        StringBuilder sb = new StringBuilder();

        helper(arr, sb, res, 0, k);
        return res;
    }

    private void helper(char[] arr, StringBuilder sb, List<String> res, int index, int k) {
        if (sb.length() == k) {
            res.add(sb.toString());
            return;
        }
        if (index == arr.length) {
            return;
        }
        helper(arr, sb, res, index + 1, k);

        sb.append(arr[index]);
        helper(arr, sb, res, index + 1, k);
        sb.deleteCharAt(sb.length() - 1);

    }
}
