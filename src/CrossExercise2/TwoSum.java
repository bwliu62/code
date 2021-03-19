package CrossExercise2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Determine if there exist two elements in a given array, the sum of which is the given target number.
 * <p>
 * Assumptions
 * <p>
 * The given array is not null and has length of at least 2
 * ​Examples
 * <p>
 * A = {1, 2, 3, 4}, target = 5, return true (1 + 4 = 5)
 * <p>
 * A = {2, 4, 2, 1}, target = 4, return true (2 + 2 = 4)
 * <p>
 * A = {2, 4, 1}, target = 4, return false
 */
public class TwoSum {
    // Method 1: TC : O(N), SC : O(N)
    public boolean existSum(int[] array, int target) {
        // Write your solution here
        if (array == null || array.length == 0) {
            return false;
        }

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < array.length; i++) {
            if (set.contains(target - array[i])) {
                return true;
            } else {
                set.add(array[i]);
            }
        }
        return false;
    }

    // Method 2 : TWO pointer
    // TC O(N)  SC O(1)
    public boolean existSum2(int[] array, int target) {
        Arrays.sort(array);
        int left = 0;
        int right = array.length - 1;
        while (left < right) {
            int sum = array[left] + array[right];
            if (sum == target) {
                return true;
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return false;
    }
}
