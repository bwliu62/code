package DP2;

public class LargestSubArraySum {
    public int largestSum(int[] array) {
        // Write your solution here
//        int[] memo = new int[array.length];
//        memo[0] = array[0];
        int globalMax = array[0];
        int curMax = array[0];
        for (int i = 1; i < array.length; i++) {
//            memo[i] = Math.max(memo[i - 1] + array[i], array[i]);
//            globalMax = Math.max(globalMax, memo[i]);
            curMax = Math.max(curMax + array[i], array[i]);
            globalMax = Math.max(curMax, globalMax);
        }
        return globalMax;
    }

    public static void main(String[] args) {
        int[] array = {1,2,4,-1,-2,10,-1,-100,-1,10,20};
        LargestSubArraySum l = new LargestSubArraySum();
        System.out.println(l.largestSum(array));
    }
}
