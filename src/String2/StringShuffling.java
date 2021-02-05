package String2;
import java.util.*;

/*
Given an array of elements, reorder it as follow:

{ N1, N2, N3, …, N2k } → { N1, Nk+1, N2, Nk+2, N3, Nk+3, … , Nk, N2k }

{ N1, N2, N3, …, N2k+1 } → { N1, Nk+1, N2, Nk+2, N3, Nk+3, … , Nk, N2k, N2k+1 }

Try to do it in place.

Assumptions

The given array is not null
Examples

{ 1, 2, 3, 4, 5, 6} → { 1, 4, 2, 5, 3, 6 }

{ 1, 2, 3, 4, 5, 6, 7, 8 } → { 1, 5, 2, 6, 3, 7, 4, 8 }

{ 1, 2, 3, 4, 5, 6, 7 } → { 1, 4, 2, 5, 3, 6, 7 }
* **/
public class StringShuffling {
    public int[] reorder(int[] array) {
        // Write your solution here
        if (array.length == 0) {
            return array;
        }
        if (array.length % 2 == 0) {
            helper(array, 0, array.length - 1);
        } else {
            helper(array, 0, array.length - 2);
        }
        return array;
    }
    private void helper(int[] array, int left, int right) {
        // base case
        int size = right - left + 1;
        if (size <= 2) {
            return;
        }

        int mid = left + size / 2;
        int lm = left + size / 4;
        int rm = left + size * 3 / 4;

        // 顺序很重要，不然写法不同！！！ 先各自reverse， 再reverse整个。
        reverse(array, lm, mid - 1); // reverse c2
        reverse(array, mid, rm - 1); // reverse c3
        reverse(array, lm, rm - 1); // reverse c2 c3

        // 先reverse 整个 c2+ c3， 再reverse 左边， 再reverse 右边。
//        reverse(array, lm, rm- 1);
//        reverse(array, lm, lm + (rm - mid) - 1);
//        reverse(array, lm + (rm - mid), rm - 1);

        helper(array, left, left + (lm - left) * 2 - 1);
        helper(array, left + (lm - left) * 2, right);
    }

    private void reverse (int[] array, int left, int right) {
        while (left < right) {
            int temp = array[left];
            array[left] = array[right];
            array[right] = temp;
            left++;
            right--;
        }
    }
}
