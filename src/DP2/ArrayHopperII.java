package DP2;

public class ArrayHopperII {
    public int minJump(int[] array) {
        // Assumption: array is not null and is not empty;
        int length = array.length;
        // minJump record the min number of jumps from 0 to each of the indices.
        int[] minJump = new int[length];
        // we don't need to jump for index 0;
        minJump[0] = 0;
        for (int i = 1; i < length; i++) { // 大循环 遍历整个array
            minJump[i] = -1; // initialized as unreachable.
            for (int j = i - 1; j >= 0; j--) { // 小循环，遍历 index = i 之前的每一个memo，更新最小步数
                if (j + array[j] >= i && minJump[j] != -1) { // j + array[j] >= i 代表之前的index = j 能跳到i。
                    // 且不等于-1代表走不到 index = i,如果算进去-1， 后面取最小值，就会一直是-1。
                    // -1 represents the case when we cannot reach the final destination
                    if (minJump[i] == -1 || minJump[i] > minJump[j] + 1) { // 如果 minJump[i] == -1, 代表第一次从 j 跳到 i.
                        // 或者 更新最小步数
                        minJump[i] = minJump[j] + 1;
                    }
                }
            }
        }
        return minJump[length - 1];
    }

    public static void main(String[] args) {
        ArrayHopperII a = new ArrayHopperII();
        int[] arr = {3, 3, 1, 0, 4};
        int[] arr1 = {2, 1, 1, 0, 2};
        System.out.println(a.minJump(arr));
//        System.out.println(a.minJump(arr1));
    }
}
