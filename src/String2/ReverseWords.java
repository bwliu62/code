package String2;

/*
Reverse the words in a sentence.

Assumptions

Words are separated by single space

There are no heading or tailing white spaces

Examples

“I love Google” → “Google love I”

Corner Cases

If the given string is null, we do not need to do anything.
**/
public class ReverseWords {
    // Assumptions:
    // 1). The words are separated  by one space character.
    // 2). There are no leading or trailing spaces.
    // 3). input is not null.
    public String reverseWords(String input) {
        // try to convert it to char array and solve the problem in-place.
        char[] array = input.toCharArray();
        // 1. reverse the whole char array;
        reverse(array, 0, array.length - 1);
        int start = 0;
        // 2. reverse each of words.
        for (int i = 0; i < array.length; i++) {
            // the start index of a word.
            if (array[i] != ' ' && (i == 0 || array[i - 1] == ' ')) {
                start = i;
            }
            // the end index of a word.
            if (array[i] != ' ' && (i == array.length - 1 || array[i + 1] == ' ')) {
                reverse(array, start, i);
            }
        }
        return new String(array);
    }

    private void reverse(char[] array, int left, int right) {
        while (left < right) {
            char temp = array[left];
            array[left] = array[right];
            array[right] = temp;
            left++;
            right--;
        }
    }
    // =====================sample code=====================

    // =====================My version=====================
    public String reverseWordsMyVersion(String input) {
        // Write your solution here
        if (input.length() <= 1) {
            return input;
        }
        char[] array = input.toCharArray();
        // step 1: reverse the whole sentence.
        reverseSentenceMyVersion(array, 0, array.length - 1);
        // step 2: reverse single word
        reverseWordMyVersion(array);

        return new String(array, 0, array.length);
    }

    private void reverseSentenceMyVersion(char[] array, int left, int right) {
        while (left <= right) {
            swapMyVersion(array, left, right);
            left++;
            right--;
        }


    }

    private void reverseWordMyVersion(char[] array) {
        int i = 0;
        int j;
        int x;
        for (x = 0; x < array.length; x++) {
            if (array[x] == ' ') { // x 停在空格
                j = x - 1;
                reverseSentenceMyVersion(array, i, j);
                i = x + 1;
            }
        }
        // post-processing 处理最后一个单词
        j = x - 1;
        reverseSentenceMyVersion(array, i, j);

    }

    private void swapMyVersion(char[] array, int left, int right) {
        char tmp = array[left];
        array[left] = array[right];
        array[right] = tmp;
    }
    // ===========================================================


    public static void main(String[] args) {
        ReverseWords r = new ReverseWords();
        System.out.println("My version: " + r.reverseWordsMyVersion("A B C D"));
        System.out.println("Sample code: " + r.reverseWords("I am a student"));

    }
}
