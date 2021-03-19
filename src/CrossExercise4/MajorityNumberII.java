package CrossExercise4;

import java.util.*;

/**
 * Given an integer array of length L, find all numbers that occur more than 1/3 * L times if any exist.
 * <p>
 * Assumptions
 * <p>
 * The given array is not null
 * Examples
 * <p>
 * A = {1, 2, 1, 2, 1}, return [1, 2]
 * A = {1, 2, 1, 2, 3, 3, 1}, return [1]
 * A = {1, 2, 2, 3, 1, 3}, return []
 */
public class MajorityNumberII {
    public List<Integer> majority(int[] array) {
        // Write your solution here
        List<Integer> res = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        float length = array.length;
        float oneOfThird = length >= 3 ? length / 3 : 1;
        // int oneOfThird = array.length / 3 + 1;

        for (int i = 0; i < array.length; i++) {
            if (map.get(array[i]) == null) { // map doesn't have this key
                if (map.size() < oneOfThird) { // if the size is less than 1/k - 1, we put it into the map
                    map.put(array[i], 1);
                } else { // if greater than 1/k - 1,
                    Iterator<Map.Entry<Integer, Integer>> iter = map.entrySet().iterator();
                    while (iter.hasNext()) {
                        Map.Entry<Integer, Integer> cur = iter.next();
                        int v = cur.getValue();
                        map.put(cur.getKey(), v - 1);
                        if (cur.getValue() == 0) { // delete key when value = 0;
                            iter.remove();
                        }
                    }
                }
            } else {
                int count = map.get(array[i]);
                map.put(array[i], count + 1);
            }
        }
        Iterator<Map.Entry<Integer, Integer>> iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<Integer, Integer> cur = iter.next();
            if (cur.getValue() > oneOfThird) { // delete key when value = 0;
                res.add(cur.getKey());
            }
        }
        return res;
    }

    public static void main(String[] args) {
        MajorityNumberII m = new MajorityNumberII();
        int[] arr = {1};
        List<Integer> res = m.majority(arr);
        for (int a: res
             ) {
            System.out.println(a);
        }
    }
}
