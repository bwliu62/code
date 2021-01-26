package test;
import java.util.ArrayList;
import java.util.List;

public class test {


    public static void main(String args[]) {
//        List<Integer> l = new ArrayList<Integer>();
//        l.add(1);
//        l.add(9);
//
//        System.out.println(l.get(0));
//        System.out.println(l.size());
        int[] arr = new int[]{3,2,1};
        partition(arr,2);
//        int[] res = removeDuplicate(arr,91);
//        for (int i : res){
//            System.out.println(i);
//        }

    }

    public static void partition(int[] array, int pivotIndex) {
        int i = 0;
        int j = array.length-1;
        while(i < j){
            if(array[i]>array[pivotIndex]){
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                j--;
            }else if(array[j] < array[pivotIndex]){
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
            }
        }
        for(int value : array){
            System.out.println(value);
        }
    }
    private static void swap(int[] array, int x, int y){
        int temp = x;
        x = y;
        y = temp;
    }
}


