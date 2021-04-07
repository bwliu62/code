package DFS2;
/**
 * Given a set of n integers, divide the set in two subsets of n/2 sizes each such that the difference of the sum of two subsets is as minimum as possible.
 *
 * Return the minimum difference(absolute value).
 *
 * Assumptions:
 *
 * The given integer array is not null and it has length of >= 2.
 * Examples:
 *
 * {1, 3, 2} can be divided into {1, 2} and {3}, the minimum difference is 0
 *
 * 思路就是 array的 permutation， 只是index 要从array的后一半开始。 用swap，swap解题
 * */
public class TwoSubsetsWithMinDifference {
    public int minDifference(int[] array) {
        // Write your solution here
        int[] global = {Integer.MAX_VALUE};

        helper(array, global, array.length / 2);
        return global[0];
    }

    private void helper(int[] array, int[] global, int index) {
        if (index == array.length) {
            return;
        }

        for (int i = 0; i < array.length / 2; i++) {
            swap(array, i, index);

            int tmp = getDiff(array);
            if (tmp < global[0]) {
                global[0] = tmp;
            }

            helper(array, global, index + 1);
            swap(array, i, index);
        }
    }

    private void swap(int[] array, int left, int right) {
        int tmp = array[left];
        array[left] = array[right];
        array[right] = tmp;
    }

    private int getDiff(int[] array) {
        int sum1 = 0;
        int sum2 = 0;

        for (int i = 0; i < array.length / 2; i++) {
            sum1 += array[i];
        }

        for (int j = array.length / 2; j < array.length; j++) {
            sum2 += array[j];
        }

        return Math.abs(sum1 - sum2);
    }
}
