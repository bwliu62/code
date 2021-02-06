package String2;
/*
Given a string, replace adjacent, repeated characters with the character followed by the number of repeated occurrences.

Assumptions

The string is not null

The characters used in the original string are guaranteed to be ‘a’ - ‘z’

Examples

“abbcccdeee” → “a1b2c3d1e3”
**/
public class CompressString2 {
    public String compress(String input) {
        // Write your solution here
        if (input == null || input.isEmpty()) {
            return input;
        }
        char[] array = input.toCharArray();
        return encode(array);
    }
    private String encode(char[] input) {
        // Step1: deal with the cases where the adjacent occurrence of the letters >= 2
        int slow = 0;
        int fast = 0;
        int newLength = 0;
        while(fast < input.length) {
            int begin = fast;
            while (fast < input.length && input[fast] == input[begin]) { // 找相同的字母 [begin,fast) 中都是相同的。
                fast++;
            }
            input[slow++] = input[begin]; // 把slow指针放到这一类相同字母的第一个
            if (fast - begin == 1) { // 如果fast - begin = 1， 意味着是单字母，此时需要扩容.+2： 一个字母+一个数字的长度
                newLength += 2;
            }else {
                // 如果不是单字母，调用copyDigits函数，在 char array中写入有多少个数字，如 传入 aaa --> a3，返回的是数字(3)占的空间大小
                int len = copyDigits(input, slow, fast - begin);
                slow += len; // 然后移动len个长度的slow指针 到数字的末尾 。
                newLength += len + 1;  // len 是数字长度，比如23 是2， + 1 代表字母长度。
            }
        }

        // Step 2: deal with the cases where the adjacent occurrence of the letters == 1.
        // Notice: if it is required to do this in place, usually the input array is a sufficient large one,
        // you will not need to allocate a new array. This solution is only for demonstration.
        // 从while中跳出的时候代表着所有非单个字母的已经处理完了.  input = [aacbbb] -> input=[a2cb3b] 此时
        //                                                                                 s  f

        char[] result = new char[newLength];
        fast = slow - 1;    // fast 停在已经处理好的string的最后一位。 slow的前一格 为什么呢？ 因为slow的左侧是处理好的，所以slow-1。
        slow = newLength - 1;  // slow 停在最后面，slow的右边是需要keep的。
        while (fast >= 0) {
            if (Character.isDigit(input[fast])) {
                while (fast >=0 && Character.isDigit(input[fast])) {
                    result[slow--] = input[fast--]; // 拷贝所有数字
                }
            } else {
                result[slow--] = '1';
            }
            result[slow--] = input[fast--]; // 拷贝完数字就要拷贝字母，必须执行的一次。
        }
        return new String(result);
    }

    // copy "count" as digits into "input", starting at "index".
    private int copyDigits(char[] input, int index, int count) {
        int len = 0;
        for (int i = count; i > 0; i /= 10) {
            index++;
            len++;
        }
        for (int i = count; i > 0; i /=10) {
            int digit = i % 10;
            input[--index] = (char) ('0' + digit);
        }
        return len;
    }

    public static void main(String[] args) {
        CompressString2 c = new CompressString2();
        String res = c.compress("aacbbb");
        System.out.println(res);
    }
}
