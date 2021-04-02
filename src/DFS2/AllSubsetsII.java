package DFS2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 *Given a set of characters represented by a String, return a list containing all subsets of the characters. Notice that each subset returned will be sorted to remove the sequence.
 *
 * Assumptions
 *
 * There could be duplicate characters in the original set.
 * Examples
 *
 * Set = "abc", all the subsets are ["", "a", "ab", "abc", "ac", "b", "bc", "c"]
 * Set = "abb", all the subsets are ["", "a", "ab", "abb", "b", "bb"]
 * Set = "abab", all the subsets are ["", "a", "aa","aab", "aabb", "ab","abb","b", "bb"]
 * Set = "", all the subsets are [""]
 * Set = null, all the subsets are []
 * */
public class AllSubsetsII {
    public List<String> subSets(String set) {
        // Write your solution here.
        List<String> res = new ArrayList<>();
        if (set == null) {
            return res;
        }

        char[] arr = set.toCharArray();
        Arrays.sort(arr);
        StringBuilder sb = new StringBuilder();
        helper(arr, sb, res, 0);

        return res;
    }

    private void helper(char[] arr, StringBuilder sb, List<String> res, int index) {
        if (index == arr.length) {
            res.add(sb.toString());
            return;
        }

        sb.append(arr[index]);
        helper(arr, sb, res, index + 1);
        sb.deleteCharAt(sb.length() - 1);

        while (index < arr.length - 1 && arr[index] == arr[index + 1]) {
            index++;
        }
        helper(arr, sb, res, index + 1);
    }
}
