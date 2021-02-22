package String2;

/*
Given a string, a partitioning of the string is a palindrome partitioning if every partition is a palindrome.

For example, “aba |b | bbabb |a| b| aba” is a palindrome partitioning of “ababbbabbababa”.

Determine the fewest cuts needed for palindrome partitioning of a given string.

For example,

minimum 3 cuts are needed for “ababbbabbababa”. The three cuts are “a | babbbab | b | ababa”.

If a string is palindrome, then minimum 0 cuts are needed.

Return the minimum cuts.

TC: O(n^2) -> The nested for-loop is n^2
SC: O(n^2 + n) = O(n) -> We need a 2D array to hold record and an 1D array to hold the # of cuts
* **/
public class StringPalindrome {
    // method 1
    public int minCut(String s) {
        if (s.length() <= 1) {
            return 0;
        }

        char[] c = s.toCharArray();
        int n = c.length;
        // isPal[i][j] means the substring from index i to index j is palindrome or not (head & tail-inclusive).
        boolean[][] isPal = new boolean[n][n];

        // cut[i] means the minimum number of cuts needed to cut the substring from index 0 to i, such that all
        // parts after cut are palindromes (head & tail-inclusive).
        int[] cut = new int[n];
        for (int i = 0; i < n; i++) {
            int minCut = i; // The max # of cuts for a string is its length
            for (int j = 0; j < i; j++) {
                if (c[j] == c[i] && (i - j <= 2 || isPal[j + 1][i - 1])) {
                    isPal[j][j] = true;
                    // update the minCut.
                    // Suppose our string is :
                    // A    B   A   |   C   C
                    //          j-1     j   i
                    // we already know the minimum number of cuts need to properly cut the substring from 0 to j - 1,
                    // and # of cuts can be direct read from the table cut[] (cut[j - 1]).
                    // So all we have to do is to put one more cut after j - 1, before j.
                    // So the minCut for position j is cut[j - 1] + 1
                    // If j is 0, then we need no cut.
                    // 左大段右小段， 左边已经process完成直接查表(cut[j - 1]), 右边不用切因为已经是palindrome. 只要在左大段和右小段之间再切一刀
                    minCut = j == 0 ? 0 : Math.min(minCut, cut[j - 1] + 1);
                }
            }
            cut[i] = minCut;
        }
        return cut[n - 1];
    }

    // method 2
    public int minCut2(String input) {
        // TC = O(N^2) SC: O(n^2 + n)
        if (input == null || input.length() <= 1) {
            return 0;
        }
        char[] array = input.toCharArray();
        int n = array.length;
        int[] M = new int[n + 1];
        M[0] = 0;
        M[1] = 0;
        for (int i = 2; i < M.length; i++) {
            int minCutForI = Integer.MAX_VALUE;
            for (int j = 0; j < i; j++) {
                if (isPalindrome(array, j, i - 1)) {
                    if (j == 0) {
                        minCutForI = 0;
                        break;
                    } else {
                        minCutForI = Math.min(minCutForI, M[j] + 1);
                    }
                }
            }
            M[i] = minCutForI;
        }
        return M[n];
    }

    private boolean isPalindrome(char[] array, int i, int j) {
        while (i < j) {
            if (array[i] != array[j]) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public static void main(String[] args) {
        StringPalindrome p = new StringPalindrome();
        String s = "abacc";
        System.out.println(p.minCut(s));
    }
}
