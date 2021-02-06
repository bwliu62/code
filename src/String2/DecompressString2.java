package String2;

/*
Given a string in compressed form, decompress it to the original string.
The adjacent repeated characters in the original string are compressed to have the character followed
by the number of repeated occurrences.

Assumptions:

The string is not null

The characters used in the original string are guaranteed to be ‘a’ - ‘z’

There are no adjacent repeated characters with length > 9

Examples

“a1c0b2c4” → “abbcccc”
* **/
public class DecompressString2 {
    // in place method:
    // When we say in place, it usually means the input is a long enough char array, and the original string only occupies
    // part of the array starting from index 0, and usually the length to represent the original string is given.
    public String decompress1(String input) {
        if (input.isEmpty()) {
            return input;
        }

        char[] array = input.toCharArray();
        // We need to handle the
        // "a0","a1","a2" case ( the decoded string is shorter) and
        // "a3", "a4" ... case ( the decoded string is longer)
        // in two pass to avoid conflict, since the encoding of the two cases require different directions.
        return decodeLong(array, decodeShort(array));
    }

    // return the length of the decoded string,
    // only cares about "a0" , "a1", "a2", A.K.A the decoded string is shorter.
    private int decodeShort(char[] input) { // "a0","a1","a2" 这三种case，不需要增加长度 都在[0,2]
        // since the decoded string is shorter, we should do the decoding work from left to right direction.
        int end = 0;
        for (int i = 0; i < input.length; i += 2) { // i += 2, 每次找到首字母
            int digit = getDigit(input[i + 1]); // i + 1 找到字母后面的数字
            if (digit >= 0 && digit <= 2) { // "a0","a1","a2" 这三种case
                for (int j = 0; j < digit; j++) {
                    input[end++] = input[i];
                }
            } else {
                // we don't handle the longer decoded string here.
                input[end++] = input[i];
                input[end++] = input[i + 1];
            }
        }
        return end;
    }

    // take care of "a3", "a4", "a5", ....
    // the decoded string is longer.
    // length : the length of the valid partition starting from index 0.
    private String decodeLong(char[] input, int length) {
        // we need to calculate the new required length.
        int newLength = length;
        for (int i = 0; i < length; i++) {
            int digit = getDigit(input[i]);
            if (digit > 2 && digit <= 9) {
                newLength += digit - 2;
            }
        }
        // NOTICE: if it's required to do this in place, usually the input array is a sufficient large one,
        // you will not need to allocate a new array. This solution is only for demonstration.
        char[] result = new char[newLength];
        int end = newLength - 1;
        for (int i = length - 1; i >= 0; i--) {
            int digit = getDigit(input[i]);
            if (digit > 2 && digit <= 9) {
                i--;
                for (int j = 0; j < digit; j++) {
                    result[end--] = input[i];
                }
            } else {
                // we already take care the shorter cases, "a1" in previous pass.
                // we can leave as it is. e.g. the input could be "abc2".
                result[end--] = input[i];
            }
        }
        return new String(result);
    }

    private int getDigit(char digit) {
        return digit - '0';
    }

    // method2: using StringBuilder to help.
    public String decompress2(String input) {
        // Assumptions: input is not null.
        char[] array = input.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            char ch = array[i++];
            int count = array[i] - '0';
            for (int c = 0; c < count; c++) {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        DecompressString2 d = new DecompressString2();
        String res1 = d.decompress1("x2y0i0z3");
        String res2 = d.decompress2("x2y0i0z3");
        System.out.println("res1: " + res1);
        System.out.println("res2: " + res2);
        //res1: xxzzz
        //res2: xxzzz
    }
}
