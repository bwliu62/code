package CrossExercise1;

import java.util.Arrays;

/*
Given a sorted integer array, remove duplicate elements. For each group of elements with the same value do not keep any of them. Do this in-place, using the left side of the original array and and maintain the relative order of the elements of the array. Return the array after deduplication.

Assumptions

The given array is not null
Examples

{1, 2, 2, 3, 3, 3} → {1}
* **/
public class ArrayDeduplication3 {
    public int[] dedup(int[] array) {
        int fast = 0;
        int slow = 0;
        while (fast < array.length) {
            int fast2 = fast + 1;
            while (fast2 < array.length && array[fast2] == array[fast]) {
                fast2++;
            }
            if (fast2 - fast == 1) {
                array[slow++] = array[fast];
            }
            fast = fast2;
        }
        return Arrays.copyOf(array, slow);
    }

    public int[] dedup2(int[] array) {
        // Write your solution here
        if (array == null || array.length == 0) {
            return array;
        }
        // 2,1,2,3,3,4,5,5,5
        //       i
        //   e
        // f
        int end = 0;
        // use flag to see if there is any duplicates of array[end].
        boolean flag = false;
        for (int i = 1; i < array.length; i++) {
            if (array[i] == array[end]) {
                // if there is duplicate , set flag and do nothing.
                flag = true;
            } else if (flag == true) {
                // if array[i] != array[end] , and flag is set, array[end] should not be included in the valid subarray,
                // and we can just replace it with array[i] since next we are going to check if there is any duplicate
                // of array[i].
                array[end] = array[i];
                flag = false;
            } else {
                // if array[i] != array[end] and flag is not set,
                // it means there is no duplicate of array[end] and it should be included in the valid subarray.
                array[++end] = array[i];
            }
        }
        // do not forget that we need to check if there is any duplicates for the last array[end]/
        return Arrays.copyOf(array, flag ? end : end + 1);
    }


    public static void main(String[] args) {
        int[] arr = {1, 1, 2, 3, 3, 4, 5, 5, 5};
        ArrayDeduplication3 a = new ArrayDeduplication3();
        for (int i : a.dedup(arr)
        ) {
            System.out.println(i);
        }
    }
}
