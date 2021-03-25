package FinalExam;
/**
 * After the event, our company will take the students out for dinner. The restaurant has a large round table that can fit the whole party. We want to know if we can arrange the students so that the names of all students around the table form an “infinite loop.” For each pair of neighboring students s1 and s2, the last letter of s1’s name must be identical to the first letter of s2’s name.
 *
 * For example, “ALICE” and “ERIC” can sit together, but “ALICE” and “BOB” cannot.
 *
 * Given an array of names, determine if it is possible to arrange the students at the round table in this way.
 *
 * Input: an array of names. Each name contains capital letters only.
 *
 * Output: true or false.
 *
 * Example
 *
 * Input: String[] = {“ALICE”, “CHARLES”, “ERIC”, “SOPHIA”}
 *
 * Output: true
 * */
public class InfiniteLoopAroundtheDinnerTable {
    public boolean canFormLoop(String[] array) {
        return helper(array, 1);
    }

    private boolean helper(String[] array, int i) {
        if (i == array.length) {
            return canSit(array[i - 1], array[0]);
        }
        for (int j = i; j < array.length; j++) {
            if (canSit(array[i - 1],array[j])) {
                swap(array, i, j);
                if (helper(array, i + 1)) {
                    return true;
                }
                swap(array, i, j);
            }
        }
        return false;
    }

    private boolean canSit(String first, String second) {
        return first.charAt(first.length() - 1) == (second.charAt(0));
    }

    private void swap(String[] array, int i, int j) {
        String temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
