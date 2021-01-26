package BFS;

import java.util.Comparator;
import java.util.PriorityQueue;

public class KSmallestInUnsortedArray {
    public int[] kSmallest(int[] array, int k) {
        // Write your solution here
        // maintain a maxHeap
        // if (array == null) {
        //   return null;
        // }
        if (array.length == 0 || k == 0) {
            return new int[0];
        }
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2){
                if (o1.equals(o2)) {
                    return 0;
                }
                return o1 < o2 ? 1 : -1;
            }
        });
        // put first k element in maxHeap
        int i = 0;
        for (i = 0; i < k; i ++){
            maxHeap.offer(array[i]);
        }
        // compare the remaining elements, if the value smaller than the top value of maxHeap,
        for(int j = i; j < array.length - 1; j ++){
            if (array[j] < maxHeap.peek()){
                maxHeap.poll();
                maxHeap.offer(array[j]);
            }
        }
        int[] res = new int[k];
        for (int a = k - 1 ; a >= 0; a --) {
            res[a] = maxHeap.poll();
        }
        return res;
    }

    public static void main(String[] args) {
        KSmallestInUnsortedArray s = new KSmallestInUnsortedArray();
        int k = 2;
        int [] arr = new int[]{1001,1002,10003,1004,768,278,2056,3097,278};
        int[] res = s.kSmallest(arr,k);
        for(int i : res){
            System.out.println(i);
        }
    }
}
