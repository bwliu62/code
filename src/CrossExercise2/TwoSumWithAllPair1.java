package CrossExercise2;

import java.util.*;

/**
 * Find all pairs of elements in a given array that sum to the given target number. Return all the pairs of indices.
 * <p>
 * Assumptions
 * <p>
 * The given array is not null and has length of at least 2.
 * <p>
 * Examples
 * <p>
 * A = {1, 3, 2, 4}, target = 5, return [[0, 3], [1, 2]]
 * <p>
 * A = {1, 2, 2, 4}, target = 6, return [[1, 3], [2, 3]]
 */
public class TwoSumWithAllPair1 {
    public List<List<Integer>> allPairs(int[] array, int target) {
        // Write your solution here
        List<List<Integer>> res = new ArrayList<>();
        // key: number , value : list of all possible indices.
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            List<Integer> indices = map.get(target - array[i]);
            // if target - array[i] is in the map,
            // we can get all the pairs(j,i) with i as the larger index
            if (indices != null) {
                for (int j : indices) {
                    res.add(Arrays.asList(j, i));
                }
            }
            // add current index i to all the possible indices for value of array[i]
            if (!map.containsKey(array[i])) {
                map.put(array[i], new ArrayList<Integer>());
            }
            map.get(array[i]).add(i);
        }
        return res;
    }

    public static void main(String[] args) {
        TwoSumWithAllPair1 t = new TwoSumWithAllPair1();
        int[] arr = {1, 3, 2, 4};
        List<List<Integer>> res = t.allPairs(arr, 5);
    }
}
