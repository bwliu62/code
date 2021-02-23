package DP2;

/*
Given two strings of alphanumeric characters, determine the minimum number of Replace, Delete, and Insert operations needed to transform one string into the other.

Assumptions

Both strings are not null, can be empty
Examples

string one: “sigh”, string two : “asith”

the edit distance between one and two is 2 (one insert “a” at front then replace “g” with “t”).

if the i-th and j-th letters of s1 and s2 are same, we compare i - 1 letter of s1 and j - 1 letter of s2.
if i-th and j-th letters are not same, we can do replace, delete and insert with i-th letter of s1 and we have 3 cases:
1): if we do replace operation, we replace same letter in i-th position, then we consider i - 1 and j - 1.
2): if we do delete operation, we delete i-th letter, then we consider i-1 and j.
3): if we do insert operation, we insert one letter in i+1 position, then we consider i , j.

base case :
    both empty string. do nothing is wrong base case. because s1[0] != s2[0].
    s1 empty . s2 not empty, do k times insert.
    s1 not empty, s2 empty, do k times delete.
* **/
public class EditDistance {
    public int editDistance(String one, String two) {
        // Write your solution here
//        M represents number of min operations
        // Assumptions : one , two are not null.
        // Again, using distance[i][j] to represent substring(0,i) in one and substring(0,j) in two. distance ->可以说是相差几个
        int[][] distance = new int[one.length() + 1][two.length() + 1]; // 为什么+1? 为了减少corner case的处理。
        // distance[0][x] 的物理意义是把第一个String直接变成第二个string长度为x的subString。
        // distance[x][x] 是把第一个string的前x位换成第二个string的x位。

        for (int i = 0; i <= one.length(); i++) {
            for (int j = 0; j <= two.length(); j++) {
                if (i == 0) { // base case : s1 is empty
                    System.out.println("in first if: i = " + i + ", j = " + j);

                    distance[i][j] = j;

                    System.out.println("base case: s1 is empty: distance[i][j] = j =  " + distance[i][j]);
                } else if (j == 0) { // base case : s2 is empty
                    System.out.println("in second if: i = " + i + ", j = " + j);

                    distance[i][j] = i;

                    System.out.println("base case: s2 is empty: distance[i][j] = i = " + distance[i][j]);
                } else if (one.charAt(i - 1) == two.charAt(j - 1)) { // case 1: the i-th and j-th letters of s1 and s2 are same
                    System.out.println("in third if: i = " + i + ", j = " + j);

                    System.out.println("i-th letter of s1 == j-th letter of s2 : distance[i][j] = distance[i - 1][j - 1] = " + distance[i - 1][j - 1]);
                    distance[i][j] = distance[i - 1][j - 1];
                } else {
                    System.out.println("i:" + i + ", j: " + j);
                    System.out.println("distance[i - 1][j] + 1 (delete): " + (distance[i - 1][j] + 1)
                            + ", distance[i][j - 1] + 1 (insert): " + (distance[i][j - 1] + 1) + ", Min of delete and insert: "
                            + Math.min(distance[i - 1][j] + 1, distance[i][j - 1] + 1));

                    distance[i][j] = Math.min(distance[i - 1][j] + 1, distance[i][j - 1] + 1); // case 2: delete + insert

                    System.out.println("distance[i - 1][j - 1] + 1 (replace): " + (distance[i - 1][j - 1] + 1)
                            + ", distance[i][j]: " + distance[i][j]);

                    distance[i][j] = Math.min(distance[i - 1][j - 1] + 1, distance[i][j]); // case 3: replace

                    System.out.println("final distance[i][j]: " + distance[i][j]);
                }
                System.out.println(" ======= ");
            }
        }
        return distance[one.length()][two.length()];
    }

    public static void main(String[] args) {
        EditDistance e = new EditDistance();
        String one = "a";
        String two = "abc";
        System.out.println(e.editDistance(one, two));
    }
}
