package recursion2;

import java.util.*;

/*
raverse an N * N 2D array in spiral order clock-wise starting from the top left corner.
Return the list of traversal sequence.

Assumptions

The 2D array is not null and has size of N * N where N >= 0
Examples

{ {1,  2,  3},

  {4,  5,  6},

  {7,  8,  9} }

the traversal sequence is [1, 2, 3, 6, 9, 8, 7, 4, 5]


* **/
public class SpiralOrderTraverseI {
    public List<Integer> spiral(int[][] matrix) {
        // Write your solution here、
        List<Integer> res = new ArrayList<>();
        if (matrix == null) {
            return res;
        }

        helper(res, matrix, 0, matrix.length);
        return res;
    }

    private void helper(List<Integer> res, int[][] matrix, int offset, int size) {
        if (size == 0) {
            return;
        }
        if (size == 1) {
            res.add(matrix[offset][offset]);
            return;
        }
        // top left to right
        for (int i = 0; i < size - 1; i++) {
            res.add(matrix[offset][i + offset]);
        }
        // right col
        for (int i = 0; i < size - 1; i++) {
            res.add(matrix[i + offset][offset + size - 1]);
        }
        // bottom - right to left
        for (int i = size - 1; i >= 1; i--) {
            res.add(matrix[offset + size - 1][offset + i]);
        }
        // left col, bottom to up
        for (int i = size - 1; i >= 1; i--) {
            res.add(matrix[offset + i][offset]);
        }
        helper(res, matrix, offset + 1, size - 2);
    }
}
