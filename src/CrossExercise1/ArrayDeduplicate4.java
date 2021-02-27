package CrossExercise1;
/**
 * Given an unsorted integer array, remove adjacent duplicate elements repeatedly, from left to right. For each group of elements with the same value do not keep any of them.
 *
 * Do this in-place, using the left side of the original array. Return the array after deduplication.
 *
 * Assumptions
 *
 * The given array is not null
 * Examples
 *
 * {1, 2, 3, 3, 3, 2, 2} → {1, 2, 2, 2} → {1}, return {1}
 * */
import java.util.Arrays;

public class ArrayDeduplicate4 {
    public int[] dedup(int[] array) {
        // method 1: for loop
//        int end = -1;
//        for (int i = 0; i < array.length; i++) {
//            if(end == -1 || array[end] != array[i]) {
//                array[++end] = array[i];
//            } else {
//                while ( i + 1 < array.length && array[i + 1] == array[end]){
//                    i++;
//                }
//                end--;
//            }
//
//        }

        // method 2 while loop
        if (array == null || array.length <= 1) {
            return array;
        }
        int end = -1;
        int fast = 0;

        while (fast < array.length) {

            if (end == -1 || array[fast] != array[end]) {
                array[++end] = array[fast];
                fast++;
            } else {
                while (fast + 1 < array.length && array[fast + 1] == array[end]) {
                    fast++;
                }
                end--;
                fast++;
            }
        }

        return Arrays.copyOf(array, end + 1);
    }

    public static void main(String[] args) {
        ArrayDeduplicate4 a = new ArrayDeduplicate4();
        int[] arr = {1, 2, 3, 3, 2};
        for (int i : a.dedup(arr)
        ) {
            System.out.print(i + " ");
        }
    }
}
