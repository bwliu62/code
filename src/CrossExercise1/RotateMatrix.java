package CrossExercise1;

import java.util.Arrays;

public class RotateMatrix {
    // Method 1: split into levels and for each level, split it into four partitions.
    public void rotate(int[][] matrix) {
        // Assumptions: matrix is not null and has size N * N , N >= 0.
        int n = matrix.length;
        if (n <= 1) {
            return;
        }
        int round = n / 2;
        for (int level = 0; level < round; level++) { // 外层循环控制圈数
            int left = level;
            int right = n - 2 - level;
            for (int i = left; i <= right; i++) { // 内层循环做交换操作   从左到右
                int tmp = matrix[left][i]; // 左上角的点  left = 0, i = 0, n = 3  tmp = [0][0]
                matrix[left][i] = matrix[n - 1 - i][left]; // 左上角 = 左下角  [0][0] = [2][0]
                matrix[n - 1 - i][left] = matrix[n - 1 - left][n - 1 - i]; // 左下角 = 右下角 [2][0] = [2][2]
                matrix[n - 1 - left][n - 1 - i] = matrix[i][n - 1 - left]; // 右下角 = 右上角 [2][2] = [0][2]
                matrix[i][n - 1 - left] = tmp; //  右上角 = 左上角 [0][2] = tmp
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3},
                          {8, 9, 4},
                          {7, 6, 5}};

        RotateMatrix r = new RotateMatrix();
        r.rotate(matrix);
        for (int[] i : matrix
             ) {
            System.out.println(Arrays.toString(i));
        }
    }
}
