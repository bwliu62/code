package DP2;

import java.util.HashSet;
import java.util.Set;

/*
Given a word and a dictionary, determine if it can be composed by concatenating words from the given dictionary.

Assumptions

The given word is not null and is not empty
The given dictionary is not null and is not empty and all the words in the dictionary are not null or empty
Examples

Dictionary: {“bob”, “cat”, “rob”}

Word: “robob” return false

Word: “robcatbob” return true since it can be composed by "rob", "cat", "bob"
* **/
public class DictionaryWordI {
    public boolean canBreak(String input, String[] dict) {
        // Write your solution here
        Set<String> dicSet = toSet(dict);
        // NOTICE: sometimes it will be handy to have such index matching, canBreak[i] represents index(i-1) in input,
        // also can represent the substring(0,i).
        // M[i] 代表前i个字母是否能被分解为一个或多个单词在dict里面。
        // M[j] 相当于左大段，查表， 0 <= j < i。 如果在就是true。
        // 同时 右小段的 string[j,i-1] 必须在dict里面。
        boolean[] canBreak = new boolean[input.length() + 1]; // include 0.
        canBreak[0] = true; // base case ， 每个单词用0次。 空字符串 ""
        for (int i = 1; i < canBreak.length; i++) {
            // enumerate the rightmost split
            for (int j = 0; j < i; j++) {
                // cut at the left of input[j].
                // check the subproblem and check the rest of the string.
                System.out.println(input);
                System.out.println("j: " + j + ", i:" + i + " " + "左大段查表：M[j]: " + canBreak[j] + ", check dic for subString (右小段): "
                        + input.substring(j, i) + "是否在dic内： " + dicSet.contains(input.substring(j, i)));
                if (dicSet.contains(input.substring(j, i)) && canBreak[j]) {
                    System.out.println("enter if, " + "j: " + j + ", i: " + i);
                    canBreak[i] = true;
                    break;
                }
            }
            System.out.println("======");
        }
        return canBreak[canBreak.length - 1];
    }

    private Set<String> toSet(String[] dict) {
        Set<String> set = new HashSet<>();
        for (String s : dict) {
            set.add(s);
        }
        return set;
    }

    public static void main(String[] args) {
        DictionaryWordI d = new DictionaryWordI();
        String[] dict = {"rob", "cat", "d"};
        String input = "robcatd";
        boolean res = d.canBreak(input, dict);
        System.out.println(res);
    }
    /*
    * robcatd
    j: 0, i:1 左大段查表：M[j]: true, check dic for subString (右小段): r是否在dic内： false
    ======
    robcatd
    j: 0, i:2 左大段查表：M[j]: true, check dic for subString (右小段): ro是否在dic内： false
    robcatd
    j: 1, i:2 左大段查表：M[j]: false, check dic for subString (右小段): o是否在dic内： false
    ======
    robcatd
    j: 0, i:3 左大段查表：M[j]: true, check dic for subString (右小段): rob是否在dic内： true
    enter if, j: 0, i: 3
    ======
    robcatd
    j: 0, i:4 左大段查表：M[j]: true, check dic for subString (右小段): robc是否在dic内： false
    robcatd
    j: 1, i:4 左大段查表：M[j]: false, check dic for subString (右小段): obc是否在dic内： false
    robcatd
    j: 2, i:4 左大段查表：M[j]: false, check dic for subString (右小段): bc是否在dic内： false
    robcatd
    j: 3, i:4 左大段查表：M[j]: true, check dic for subString (右小段): c是否在dic内： false
    ======
    robcatd
    j: 0, i:5 左大段查表：M[j]: true, check dic for subString (右小段): robca是否在dic内： false
    robcatd
    j: 1, i:5 左大段查表：M[j]: false, check dic for subString (右小段): obca是否在dic内： false
    robcatd
    j: 2, i:5 左大段查表：M[j]: false, check dic for subString (右小段): bca是否在dic内： false
    robcatd
    j: 3, i:5 左大段查表：M[j]: true, check dic for subString (右小段): ca是否在dic内： false
    robcatd
    j: 4, i:5 左大段查表：M[j]: false, check dic for subString (右小段): a是否在dic内： false
    ======
    robcatd
    j: 0, i:6 左大段查表：M[j]: true, check dic for subString (右小段): robcat是否在dic内： false
    robcatd
    j: 1, i:6 左大段查表：M[j]: false, check dic for subString (右小段): obcat是否在dic内： false
    robcatd
    j: 2, i:6 左大段查表：M[j]: false, check dic for subString (右小段): bcat是否在dic内： false
    robcatd
    j: 3, i:6 左大段查表：M[j]: true, check dic for subString (右小段): cat是否在dic内： true
    enter if, j: 3, i: 6
    ======
    robcatd
    j: 0, i:7 左大段查表：M[j]: true, check dic for subString (右小段): robcatd是否在dic内： false
    robcatd
    j: 1, i:7 左大段查表：M[j]: false, check dic for subString (右小段): obcatd是否在dic内： false
    robcatd
    j: 2, i:7 左大段查表：M[j]: false, check dic for subString (右小段): bcatd是否在dic内： false
    robcatd
    j: 3, i:7 左大段查表：M[j]: true, check dic for subString (右小段): catd是否在dic内： false
    robcatd
    j: 4, i:7 左大段查表：M[j]: false, check dic for subString (右小段): atd是否在dic内： false
    robcatd
    j: 5, i:7 左大段查表：M[j]: false, check dic for subString (右小段): td是否在dic内： false
    robcatd
    j: 6, i:7 左大段查表：M[j]: true, check dic for subString (右小段): d是否在dic内： true
    enter if, j: 6, i: 7
    ======
    true
    * */


}
