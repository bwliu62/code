package HashTableAndString1;

public class RemoveDuplicateIV {
    public String deDup(String input) {
        // Write your solution here
        if (input == null || input.length() <= 1) {
            return input;
        }
        // try to convert the string to char[], and do it in-place
        char[] array = input.toCharArray();
        // instead of using a extra stack explicitly, we can actually
        // reuse the left side of the original char[] as the 'stack'.
        // end : is where the top of stack is.
        int end = 0;
        for (int i = 1; i < array.length; i++) {
            // if the stack is empty(when end == -1) or there is no dupicate chars,
            // we are able to push the character into the stack.
            if (end == -1 || array[i] != array[end]) {
                array[++end] = array[i]; // need to ++end, because 1. if end == -1, cause error.
                                        // 2. end means the top of stack, we need to move it first, and then
                                        // add element on this index, otherwise we will replace origin number.
            } else {
                // otherwise, we need pop the top element by end--;
                // and ignore all the consecutive duplicate chars.
                end--;
                while (i + 1 < array.length && array[i] == array[i + 1]) {
                    i++;
                }
            }
        }
        return new String(array, 0, end + 1);
    }

    public static void main(String[] args) {
        RemoveDuplicateIV r = new RemoveDuplicateIV();
        String a = r.deDup("aababab");
        System.out.println(a);
        // a = babab
    }
}
