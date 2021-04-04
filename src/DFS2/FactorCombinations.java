package DFS2;

import java.util.ArrayList;
import java.util.List;

public class FactorCombinations {
    public List<List<Integer>> combinations(int target) {
        // Write your solution here
        List<List<Integer>> res = new ArrayList<>();
        if (target <= 1) {
            return res;
        }

        List<Integer> cur = new ArrayList<>();
        List<Integer> factors = getFactors(target);
        helper(target, factors, 0, cur, res);
        return res;
    }

    private void helper(int target, List<Integer> factors, int index, List<Integer> cur, List<List<Integer>> res) {
        if (index == factors.size()) {
            if (target == 1) {
                res.add(new ArrayList<>(cur));
            }
            return;
        }

        helper(target, factors, index + 1, cur, res);

        int factor = factors.get(index);
        int size = cur.size();
        while (target % factor == 0) {
            cur.add(factor);
            target /= factor;
            helper(target, factors, index + 1, cur, res);
        }
        cur.subList(size, cur.size()).clear();
    }

    private List<Integer> getFactors(int target) {
        List<Integer> res = new ArrayList<>();
        for (int i = 2; i <= target / 2; i++) {
            if (target % i == 0) {
                res.add(i);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        FactorCombinations f = new FactorCombinations();
        List<List<Integer>> res = new ArrayList<>();
        res = f.combinations(8);

    }
}
