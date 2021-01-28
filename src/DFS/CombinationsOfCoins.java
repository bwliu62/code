package DFS;

import java.util.ArrayList;
import java.util.List;

public class CombinationsOfCoins {
    public List<List<Integer>> combinations(int target, int[] coins){
        // each combination is represented as a List<Integer> cur,
        // and cur.get(i) = the number of coins of coins[i].
        // all the combinations are stored in the result as List of List<Integer>.
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        helper(target, coins, 0, cur, result);
        return result;
    }
        // target: remaining cents we need to complete.
        // coins: all the possible different coins.
        // index: we want to see how many coins we need for coins[index].
    private void helper(int target, int[] coins, int index, List<Integer> cur,
                        List<List<Integer>> result) {
        // terminate condition:
        // NOTICE: this can also be done at index == coins.length where all the coins
        // have been picked
        // but a probably better one is at a previous level to reduce a number of
        // unnecessary branches in the DFS.
        // think about it, coins.length - 1 represents the last coin we can sue
        // and actually what we can do at this level is to get target/ coins[coins.length - 1] coins if possible
        if (index == coins.length - 1) {
            if (target % coins[coins.length - 1] == 0) {
                cur.add(target / coins[coins.length - 1]);
                result.add(new ArrayList<Integer>(cur));
                cur.remove(cur.size() - 1);
            }
            return;
        }
        // for coins[index], we can pick 0, 1, 2, ..., target / coins[index] coins
        int max = target / coins[index];
        for (int i = 0; i <= max; i++) {
            cur.add(i);
            // remember to modify the remaining cents for the next level.
            helper(target - i * coins[index], coins, index + 1, cur, result);
            cur.remove(cur.size() - 1);
        }
    }

    public static void main(String[] args) {
        CombinationsOfCoins c = new CombinationsOfCoins();
        int target = 10;
        int[] coins = {34,31,29,16,2};
        List<List<Integer>> result = c.combinations(target,coins);
        System.out.println(result);
        // res : [[0, 0, 0, 0, 5]]
    }
}

