package CrossExercise1;

public class LongestConsecutive1s {
    public int longest(int[] array) {
        // Write your solution here
        if (array == null || array.length == 0) {
            return 0;
        }
        int max = 0;
        int cur = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 1) {
                if (i == 0 || array[i - 1] == 0) {
                    cur = 1;
                } else {
                    cur++;
                }
//                max = Math.max(cur,max);
            }
            max = Math.max(cur,max);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = {1, 0, 1, 0, 0, 1, 1, 1, 1, 0};
        LongestConsecutive1s l = new LongestConsecutive1s();
        System.out.println(l.longest(arr));
    }
}
