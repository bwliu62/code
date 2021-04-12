package DFS2;
/**
 * Given an integer k, arrange the sequence of integers [1, 1, 2, 2, 3, 3, ...., k - 1, k - 1, k, k], such that the output integer array satisfy this condition:
 *
 * Between each two i's, they are exactly i integers (for example: between the two 1s, there is one number, between the two 2's there are two numbers).
 *
 * If there does not exist such sequence, return null.
 *
 * Assumptions:
 *
 * k is guaranteed to be > 0
 * Examples:
 *
 * k = 3, The output = { 2, 3, 1, 2, 1, 3 }.
 * */
public class KeepDistanceForIdenticalElements {
    public int[] keepDistance(int k) {
        // Write your solution here.
        int[] array = new int[2 * k];
        for (int i = 0; i < k; i++) {
            array[i * 2] = i + 1;
            array[i * 2 + 1] = i + 1;
        }
        // used[i] == true if and only if i is used once
        boolean[] used = new boolean[k + 1];


        return helper1(array, 0, used) ? array : null;
    }

    private boolean helper1(int[] array, int index, boolean[] used) {
        if (index == array.length) {
            return true;
        }
        for (int i = index; i < array.length; i++) {
            int cur = array[i];
            if (!used[cur] || (index > cur && array[index - cur - 1] == cur)) {
                swap(array, index, i);
                used[cur] = !used[cur];
                if (helper1(array, index + 1, used)) {
                    return true;
                }
                swap(array, index, i);
                used[cur] = !used[cur];
            }
        }
        return false;
    }

    private void swap(int[] array, int left, int right) {
        int tmp = array[left];
        array[left] = array[right];
        array[right] = tmp;
    }

    // Method2 : another method to generate all permutations
    public int[] keepDistance2(int k) {
        int[] array = new int[2 * k];
        int[] used = new int[k + 1];
        return helper2(array, 0, used) ? array : null;
    }

    private boolean helper2(int[] array, int index, int[] used) {
        if (index == array.length) {
            return true;
        }
        for (int i = 1; i < used.length; i++) {
            if (used[i] == 0 || (used[i] == 1 && index > i && array[index - i - 1] == i)) {
                array[index] = i;
                used[i]++;
                if (helper2(array, index + 1, used)) {
                    return true;
                }
                used[i]--;
            }
        }
        return false;
    }

    public int[] keepDistance3(int k) {
        int[] array = new int[2 * k];
        return helper3(array, k) ? array : null;
    }

    private boolean helper3(int[] array, int n) {
        if (n == 0) {
            return true;
        }
        for (int i = 0; i < array.length - n - 1; i++) {
            if (array[i] == 0 && array[i + n + 1] == 0) {
                array[i] = n;
                array[i + n + 1] = n;
                if (helper3(array, n - 1)) {
                    return true;
                }
                array[i] = 0;
                array[i + n + 1] = 0;
            }
        }
        return false;
    }
}
