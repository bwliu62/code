package DFS;

import java.util.*;

public class SubSets1 {
    public List<String> subSets(String set) {
        List<String> result = new ArrayList<>();
        if (set == null) {
            return result;
        }
        char[] arraySet = set.toCharArray();
        // record the current subset
        StringBuilder sb = new StringBuilder();
        helper(arraySet, sb, 0, result);
        return result;
    }

    // at each level, determine the character at the position "index" to be picked
    private void helper(char[] set, StringBuilder sb, int index, List<String> result) {
        // terminate condition:
        // when we finishes determining for all the character pick or not
        // we have a complete subset
        if (index == set.length) {
            result.add(sb.toString());
            return;
        }
        // 1. not pick the character at index
        helper(set, sb, index + 1, result);

        // 2. pick the character at index
        helper(set, sb.append(set[index]), index + 1, result);

        sb.deleteCharAt(sb.length() - 1);
    }

    public static void main(String[] args) {
        SubSets1 s = new SubSets1();
        String string = "abc";
        List<String> result;
        result = s.subSets(string);
        for (String i : result) {
            System.out.println("**********");
            System.out.println(i);
        }
    }


}
