package FinalExam;

import java.util.*;

/**
 * Our company is organizing a series of university recruiting events. Each day, we host an event at one university, but sometimes we want to take a break for one day before moving on to the next university.
 *
 *
 *
 * Given a sequence of universities (The sequence is fixed and cannot be changed), print all possible schedules of the recruiting events.
 *
 *
 *
 * Input: a string of universities. Each university is represented as a single capital letter.
 *
 * Output: all possible schedules. A lowercase letter “x” means we take a break.
 *
 *
 *
 * Example
 *
 * Input: String = “ABC”
 *
 * Output:
 *
 * ABC
 *
 * ABxC
 *
 * AxBC
 *
 * AxBxC
 * */
public class RecruitingEventSchedules {
    public List<String> allSchedules(String input) {
        if (input == null || input.length() == 0) {
            return Collections.emptyList();
        }
        List<String> res = new ArrayList<>();
        char[] array = input.toCharArray();
        StringBuilder sb = new StringBuilder();
        helper(array, 0, sb, res);
        return res;
    }

    private void helper(char[] array, int index, StringBuilder sb, List<String> res) {
        if (index == array.length) {
            res.add(sb.toString());
            return;
        }

        sb.append(array[index]);

        // Case 1: take a break;
        if (index < array.length - 1) {
            sb.append('x');
            helper(array, index + 1, sb, res);
            sb.deleteCharAt(sb.length() - 1);
        }

        // case 2: not taking a break
        helper(array, index + 1, sb, res);

        sb.deleteCharAt(sb.length() - 1); // 还要吐一次
    }
}
