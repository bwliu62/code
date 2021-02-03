package String2;

import java.util.*;

/*
Given a string with possible duplicate characters, return a list with all permutations of the characters.

Examples

Set = “abc”, all permutations are [“abc”, “acb”, “bac”, “bca”, “cab”, “cba”]
Set = "aba", all permutations are ["aab", "aba", "baa"]
Set = "", all permutations are [""]
Set = null, all permutations are []
**/
public class Permutations2 {
    public List<String> permutations(String input) {
        List<String> result = new ArrayList<>();
        if (input == null) {
            return result;
        }

        char[] array = input.toCharArray();
        helper(array, 0, result);
        return result;
    }
    private void helper(char[] array, int index, List<String> result){
        if(index == array.length) {
            result.add(new String(array));
            return;
        }
        Set<Character> used = new HashSet<>();
        for (int i = index; i < array.length; i++) {
            if (used.add(array[i])) {
                swap(array, i, index);
                helper(array, index + 1, result);
                swap(array, i, index);
            }
        }
    }
    private void swap(char[] array, int left, int right) {
        char tmp = array[left];
        array[left] = array[right];
        array[right] = tmp;
    }

    public static void main(String[] args) {
        Permutations2 p = new Permutations2();
        List<String> result = p.permutations("abcadcd");

        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));
        }
    }
}
