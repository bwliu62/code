package CrossExercise4;

/**
 * Given two sorted arrays of integers, find the Kth smallest number.
 * <p>
 * Assumptions
 * <p>
 * The two given arrays are not null and at least one of them is not empty
 * <p>
 * K >= 1, K <= total lengths of the two sorted arrays
 * <p>
 * Examples
 * <p>
 * A = {1, 4, 6}, B = {2, 3}, the 3rd smallest number is 3.
 * <p>
 * A = {1, 2, 3, 4}, B = {}, the 2nd smallest number is 2.
 */
public class KthSmallestInTwoSortedArrays {
    // method 1 : run Binary search in two array separately.
    public int kth1(int[] a, int[] b, int k) {
        // Write your solution here
        int ans_from_a = helper(a, b, k);
        // B[k - i - 2] <= A[i] < B[k - i - 1]
        return ans_from_a == -1 ? b[helper(b, a, k)] : a[ans_from_a];
    }

    private int helper(int[] a, int[] b, int k) { // return index
        int l = 0;
        int r = Math.min(k, a.length) - 1; // only need k elements in two array, but if k is too large, we choose smaller one.

        while (l <= r) {
            int m = l + (r - l) / 2;
            if (a[m] < getIndex(b, k - m - 2)) {
                l = m + 1;
            } else if (a[m] > getIndex(b, k - m - 1)) {
                r = m - 1;
            } else {
                return m;
            }
        }
        return -1;
    }

    private int getIndex(int[] nums, int index) {
        if (0 > index) {
            return Integer.MIN_VALUE;
        } else if (index >= nums.length) {
            return Integer.MAX_VALUE;
        } else {
            return nums[index];
        }
    }

    // Method2 : run binary search in two array synchronously
    public int kth2(int[] a, int[] b, int k) {
        // Assumptions: a, b is not null, at least one of them is not empty, k <= a.length + b.length, k >= 1
        return kth2(a, 0, b, 0, k);
    }

    // in the subarray of a starting from index aleft,
    // and subarray of b starting from index bleft, find the kth smallest element among these two subarrays.
    private int kth2(int[] a, int aleft, int[] b, int bleft, int k) {
        // three base cases:
        // 1. we already eliminate all the elements in a.
        // 2. we already eleminate all the elements in b.
        // 3. when k is reduced to 1, don't miss this base case.
        // The reason why we havethis as base case is in the following logic, we need k >= 2 to make it work.
        if (aleft >= a.length) {
            return b[bleft + k - 1];
        }
        if (bleft >= a.length) {
            return a[aleft + k - 1];
        }
        if (k == 1) {
            return Math.min(a[aleft], b[bleft]);
        }
        // we compare the k/2th element in a's subarray.
        // and the k/2th element in b's subarray.
        // to determine which k/2 partition can be surely included in the smallest k elements.
        int amid = aleft + k / 2 - 1;
        int bmid = bleft + k / 2 - 1;
        int avla = amid >= a.length ? Integer.MAX_VALUE : a[amid];
        int bval = bmid >= b.length ? Integer.MAX_VALUE : b[amid];
        if (avla <= bval) {
            return kth2(a, amid + 1, b, bleft, k - k / 2);
        } else {
            return kth2(a, aleft, b, bmid + 1, k - k / 2);
        }
    }
}
