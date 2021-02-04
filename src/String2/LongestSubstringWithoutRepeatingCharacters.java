package String2;

import java.util.*;
/*
Given a string, find the longest substring without any repeating characters and return the length of it.
The input string is guaranteed to be not null.

For example, the longest substring without repeating letters for "bcdfbd" is "bcdf",
we should return 4 in this case.
* **/


public class LongestSubstringWithoutRepeatingCharacters {
    public int longest(String input) {
        // Write your solution here
        // Assumptions : the input string is not null.
        // the distinct set contains all distinct characters in the sliding window of (slow, fast).
        if (input.length() == 0) {
            return 0;
        }
        int slow = 0;
        int fast = 0;
        int longest = 0;

        Set<Character> distinct = new HashSet<>();

        while (fast < input.length()) {
            if (distinct.contains(input.charAt(fast))) {
                // if we meet the duplicate character, we need to move the slow pointer until find unique char.
                distinct.remove(input.charAt(slow++));

            } else {
                // if there is no duplicate character, we can slide fast pointer and we have a new sliding window of
                // (slow, fast) containing all distinct characters.
                distinct.add(input.charAt(fast++));
                longest = Math.max(longest, fast - slow);
            }
        }
        return longest;
    }
}
