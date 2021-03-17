package CrossExercise4;

import java.util.*;

/**
 * Given an integer array A and a sliding window of size K, find the maximum value of each window as it slides from left to right.
 * <p>
 * Assumptions
 * <p>
 * The given array is not null and is not empty
 * <p>
 * K >= 1, K <= A.length
 * <p>
 * Examples
 * <p>
 * A = {1, 2, 3, 2, 4, 2, 1}, K = 3, the windows are {{1,2,3}, {2,3,2}, {3,2,4}, {2,4,2}, {4,2,1}},
 * and the maximum values of each K-sized sliding window are [3, 3, 4, 4, 4]
 */
public class MaximumValuesOfSizeKSlidingWindows {
    public List<Integer> maxWindows(int[] array, int k) {
        // Assumptions : array is not null or not empty,
        // k >= 1 and k <= array.length;
        List<Integer> max = new ArrayList<>();
        // use a descending deque to solve this problem,
        // we store the index instead of the actual value in the deque,
        // and we make sure :
        // 1. the deque only contains index in the current sliding window.
        // 2. for any index, the previous index with smaller value is discard from the deque
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < array.length; i++) {
            // discard any index with smaller value than index i.
            while (!deque.isEmpty() && array[deque.peekLast()] <= array[i]) { // 如果deque的最右边的数小于下一个要进来的数，就把它pop出去
                deque.pollLast();
            }
            // it's possible the head element is out of the current sliding window, so we might need to discard it as well
            // 如果deque的最左边的index <= 当前sliding window 最左边的index， 就要把deque里的不属于当前window的index删掉
            if (!deque.isEmpty() && deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }

            deque.offerLast(i); // 滑动窗口进数

            if (i >= k - 1) { // 用 if 来检测是不是刚开始的前k个数
                max.add(array[deque.peekFirst()]); // 因为deque是降序排列，所以最右边的是current window里最大的，所以把最右边的数添加进去，但是不要先pop出来
            }
            helper(deque, array, max);
        }
        return max;
    }

    private void helper(Deque<Integer> deque, int[] array, List<Integer> max) {
        Iterator iterator = deque.iterator();
        while (iterator.hasNext()) {
            int a = (int) iterator.next();
            System.out.print(array[a] + " ");
        }

        Iterator iterator1 = max.iterator();
        System.out.print(" , res :");
        while (iterator1.hasNext()) {
            System.out.print(iterator1.next() + " ");
        }
        System.out.println(" ");
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 1};
        int[] array1 = {1, 3, 2, 5, 8, 9, 4, 7, 3};
        MaximumValuesOfSizeKSlidingWindows m = new MaximumValuesOfSizeKSlidingWindows();
        System.out.println(m.maxWindows(array1, 3));
    }
}
