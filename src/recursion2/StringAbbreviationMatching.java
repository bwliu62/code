package recursion2;

/*
Word “book” can be abbreviated to 4, b3, b2k, etc.
Given a string and an abbreviation, return if the string matches the abbreviation.

Assumptions:

The original string only contains alphabetic characters.
Both input and pattern are not null.
Pattern would not contain invalid information like "a0a","0".
Examples:

pattern “s11d” matches input “sophisticated” since “11” matches eleven chars “ophisticate”.
* **/
public class StringAbbreviationMatching {
    // Method 1: recursion
    public boolean match(String input, String pattern) {
        // Write your solution here
        return match(input, pattern, 0, 0);
    }

    private boolean match(String s, String t, int si, int ti) {
        // only when we run out of s and t at the same time
        // there is a match.
        if (si == s.length() && ti == t.length()) {
            return true;
        }
        // if we run out of s and t but there is still some characters left for the other one,
        // we cannot find the match.
        if (si >= s.length() || ti >= t.length()) {
            return false;
        }
        // case 1. if the current character in t is not a digit.
        if (t.charAt(ti) < '0' || t.charAt(ti) > '9') {
            if (s.charAt(si) == t.charAt(ti)) {
                return match(s, t, si + 1, ti + 1);
            }
            return false;
        }
        // case 2. if the current character in t is a digit.
        // we need to find in total what is the number.
        // e.g. "123" means number 123.
        int count = 0;
        while (ti < t.length() && t.charAt(ti) >= '0' && t.charAt(ti) <= '9') {
            count = count * 10 + (t.charAt(ti) - '0');
            ti++;
        }
        return match(s, t, si + count, ti);
    }

    // Method 2: iterative way
    // NOTICE: the above recursion solution is a TAIL recursion.
    // It's easy to convert it to an iterative way.
    public boolean matchII(String input, String pattern) {
        // Assumption : input, pattern are not null.
        int si = 0;
        int ti = 0;
        while (si < input.length() && ti < pattern.length()) {
            if (pattern.charAt(ti) < '0' || pattern.charAt(ti) > '9') {
                if (input.charAt(si) != pattern.charAt(ti)) {
                    return false;
                }
                si++;
                ti++;
            } else {
                int count = 0;
                while (ti < pattern.length() && pattern.charAt(ti) >= '0' && pattern.charAt(ti) <= '9') {
                    count = count * 10 + (pattern.charAt(ti) - '0');
                    ti++;
                }
                si += count;
            }
        }
        return si == input.length() && ti == pattern.length();
    }

    public static void main(String[] args) {
        String pattern1 = "4";
        String pattern2 = "b3";
        String pattern3 = "b2k";
        String pattern4 = "b4";

        String input = "book";

        StringAbbreviationMatching s = new StringAbbreviationMatching();
        System.out.println(s.match(input,pattern1));
        System.out.println(s.match(input,pattern2));
        System.out.println(s.match(input,pattern3));
        System.out.println(s.match(input,pattern4));

        System.out.println(s.matchII(input,pattern1));
        System.out.println(s.matchII(input,pattern2));
        System.out.println(s.matchII(input,pattern3));
        System.out.println(s.matchII(input,pattern4));
    }
}
