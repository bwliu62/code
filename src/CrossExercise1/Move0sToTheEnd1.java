package CrossExercise1;

public class Move0sToTheEnd1 {
    public int[] moveZero(int[] array) {
        // Write your solution here
        if (array == null || array.length == 0) {
            return array;
        }
        int slow = 0;
        for (int fast = 0; fast < array.length; fast++) {
            if (array[fast] != 0) {
                array[slow] = array[fast];
                slow++;
            }
        }
        while (slow < array.length) {
            array[slow] = 0;
            slow++;
        }
        return array;
    }

    public static void main(String[] args) {
        int[] arr = {0, 0, 1, 1, 0, 1, 0};
        Move0sToTheEnd1 m = new Move0sToTheEnd1();
        for (int i : m.moveZero(arr)
        ) {
            System.out.print(i + " ");
        }
    }
}
