package recursion2;

import java.util.*;

/*
Traverse an M * N 2D array in spiral order clock-wise starting from the top left corner. Return the list of traversal sequence.

Assumptions

The 2D array is not null and has size of M * N where M, N >= 0
Examples

{ {1,  2,  3,  4},

  {5,  6,  7,  8},

  {9, 10, 11, 12} }
  the traversal sequence is [1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7]

  4个for loop， 第一个traverse top  1 2 3 4, 第二个 for loop traverse 最右边一列不包括 top and bottom 两行： 8，
                第三个traverse bottom from right to left, 12 -> 11 -> 10 -> 9.
               最后一个for loop ,traverse most left column , 不包括top and bottom 所在行， 5。
  此时， 会剩余一个row ( 6, 7 )没有被traverse， 需要post-processing. 这是一个corner case need to be handled.

  另外一个corner case:

  { {1,  2,  3},

  {5,  6,  7},

  {9, 10, 11},

  {13,14, 15}  }

  此时用4个for loop traverse 结束后， 会剩下一列 (6, 10 ) 没有被traverse，此时我们需要做post-processing 处理。

  第三个 corner case： 4 * 4 的时候是没有剩余的, 3 * 3都会有，暂时觉得是偶数的不会剩.

* **/
public class SpiralOrderTraverseII {
    public List<Integer> spiral(int[][] matrix) {
        // Assumptions : matrix is not null, has size of M * N, where M, N >= 0
        List<Integer> list = new ArrayList<>();
        int m = matrix.length; // rows
        // need to handle this case since if m == 0, matrix[0].length will throw ArrayIndexOutOfBoundException
        if (m == 0) {
            return list;
        }
        int n = matrix[0].length; // columns
        int left = 0;
        int right = n - 1;
        int up = 0;
        int down = m - 1;
        // the base case is a little complicated comparing to N*N matrix.
        // 1. there is nothing left.
        // 2. there is one row left.
        // 3. there is one colum left.
        while (left < right && up < down) { // 当left == right时， 剩下column 没有处理， 当top == down, 剩下一行没有处理,
            for (int i = left; i <= right; i++) {
                list.add(matrix[up][i]);
            }
            for (int i = up + 1; i <= down - 1; i++) {
                list.add(matrix[i][right]);
            }
            for (int i = right; i >= left; i--) {
                list.add(matrix[down][i]);
            }
            for (int i = down - 1; i >= up + 1; i--) {
                list.add(matrix[i][left]);
            }
            left++;
            right--;
            up++;
            down--;
        }
        // 1. if there is nothing left.
        if (left > right || up > down) {
            return list;
        }
        // 2. if there is one column left.
        if (left == right) {
            for (int i = up; i <= down; i++) {
                list.add(matrix[i][left]);
            }
        } else {
            // 3. if there is one row left.
            for (int i = left; i <= right; i++) {
                list.add(matrix[up][i]);
            }
        }
        return list;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        int[][] matrix2 = {{1, 2, 3}, {5, 6, 7}, {9, 10, 11}};
        int[][] matrix3 = {{1, 2, 3}, {5, 6, 7}, {9, 10, 11}, {13, 14, 15}};
        int[][] matrix4 = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        SpiralOrderTraverseII s = new SpiralOrderTraverseII();
        List<Integer> list = s.spiral(matrix3);
        for (int i : list
        ) {
            System.out.print(i + " ");
        }

        /*
         * 1  2  3  4
         * 5  6  7  8
         * 9  10 11 12
         * expected result : 1 2 3 4 8 12 11 10 9 5 6 7
         * */
    }
}
