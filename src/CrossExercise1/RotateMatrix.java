package CrossExercise1;
/**
 * Rotate an N * N matrix clockwise 90 degrees.
 *
 * Assumptions
 *
 * The matrix is not null and N >= 0
 * Examples
 *
 * { {1,  2,  3}
 *
 *   {8,  9,  4},
 *
 *   {7,  6,  5} }
 *
 * after rotation is
 *
 * { {7,  8,  1}
 *
 *   {6,  9,  2},
 *
 *   {5,  4,  3} }
 * */
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
        System.out.println("n = " + n + ", round = " + round);
        System.out.println(" ");
        for (int level = 0; level < round; level++) { // 外层循环控制圈数
            int left = level;
            int right = n - 2 - level;
            System.out.println("left(level) = " + left + ", right = " + right);
            System.out.println(" ");
            for (int i = left; i <= right; i++) { // 内层循环做交换操作   从左到右
                System.out.println("i = " + i);
                int tmp = matrix[left][i]; // 左上角的点  left = 0, i = 0, n = 3  tmp = [0][0]
                System.out.println("int tmp = matrix[left][i] = matrix[" + left + "][" + i + "]" + " = " + matrix[left][i]);

                matrix[left][i] = matrix[n - 1 - i][left]; // 左上角 = 左下角  [0][0] = [2][0]
                System.out.println("matrix[left][i] = matrix[n - 1 - i][left] ");
                System.out.println("matrix[" + left + "][" + i + "]" + " = " + "matrix[" + (n - 1 - i) + "][" + left + "]");


                matrix[n - 1 - i][left] = matrix[n - 1 - left][n - 1 - i]; // 左下角 = 右下角 [2][0] = [2][2]
                System.out.println("matrix[n - 1 - i][left] = matrix[n - 1 - left][n - 1 - i]");
                System.out.println("matrix[" + (n - 1 - i) + "][" + left + "]" + " = " + "matrix[" + (n - 1 - left) + "][" + (n - 1 - i) + "]");

                matrix[n - 1 - left][n - 1 - i] = matrix[i][n - 1 - left]; // 右下角 = 右上角 [2][2] = [0][2]
                System.out.println("matrix[n - 1 - left][n - 1 - i] = matrix[i][n - 1 - left]");
                System.out.println("matrix[" + (n - 1 - left) + "][" + (n - 1 - i) + "] = " + "matrix[" + i + "][" + (n - 1 - left) + "]");

                matrix[i][n - 1 - left] = tmp; //  右上角 = 左上角 [0][2] = tmp
                System.out.println("matrix[i][n - 1 - left] = tmp");
                System.out.println("matrix[" + i + "]" + "[" + (n - 1 - left) + "] = " + tmp);
                System.out.println(" ");
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3},
                {8, 9, 4},
                {7, 6, 5}};

        int[][] matrix2 = {{1, 2, 3, 4}, {12, 13, 14, 5}, {11, 15, 16, 6}, {10, 9, 8, 7}};

        RotateMatrix r = new RotateMatrix();
        r.rotate(matrix2);
        for (int[] i : matrix2
        ) {
            System.out.println(Arrays.toString(i));
        }
    }

    /**
     * n = 4, round = 2
     *
     * left(level) = 0, right = 2
     *
     * i = 0
     * int tmp = matrix[left][i] = matrix[0][0] = 1
     * matrix[left][i] = matrix[n - 1 - i][left]
     * matrix[0][0] = matrix[3][0]
     * matrix[n - 1 - i][left] = matrix[n - 1 - left][n - 1 - i]
     * matrix[3][0] = matrix[3][3]
     * matrix[n - 1 - left][n - 1 - i] = matrix[i][n - 1 - left]
     * matrix[3][3] = matrix[0][3]
     * matrix[i][n - 1 - left] = tmp
     * matrix[0][3] = 1
     *
     * i = 1
     * int tmp = matrix[left][i] = matrix[0][1] = 2
     * matrix[left][i] = matrix[n - 1 - i][left]
     * matrix[0][1] = matrix[2][0]
     * matrix[n - 1 - i][left] = matrix[n - 1 - left][n - 1 - i]
     * matrix[2][0] = matrix[3][2]
     * matrix[n - 1 - left][n - 1 - i] = matrix[i][n - 1 - left]
     * matrix[3][2] = matrix[1][3]
     * matrix[i][n - 1 - left] = tmp
     * matrix[1][3] = 2
     *
     * i = 2
     * int tmp = matrix[left][i] = matrix[0][2] = 3
     * matrix[left][i] = matrix[n - 1 - i][left]
     * matrix[0][2] = matrix[1][0]
     * matrix[n - 1 - i][left] = matrix[n - 1 - left][n - 1 - i]
     * matrix[1][0] = matrix[3][1]
     * matrix[n - 1 - left][n - 1 - i] = matrix[i][n - 1 - left]
     * matrix[3][1] = matrix[2][3]
     * matrix[i][n - 1 - left] = tmp
     * matrix[2][3] = 3
     *
     * left(level) = 1, right = 1
     *
     * i = 1
     * int tmp = matrix[left][i] = matrix[1][1] = 13
     * matrix[left][i] = matrix[n - 1 - i][left]
     * matrix[1][1] = matrix[2][1]
     * matrix[n - 1 - i][left] = matrix[n - 1 - left][n - 1 - i]
     * matrix[2][1] = matrix[2][2]
     * matrix[n - 1 - left][n - 1 - i] = matrix[i][n - 1 - left]
     * matrix[2][2] = matrix[1][2]
     * matrix[i][n - 1 - left] = tmp
     * matrix[1][2] = 13
     *
     * [10, 11, 12, 1]
     * [9, 15, 13, 2]
     * [8, 16, 14, 3]
     * [7, 6, 5, 4]
     * */
}
