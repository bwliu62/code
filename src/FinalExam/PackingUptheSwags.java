package FinalExam;

import java.util.Arrays;

/**
 * Our company is going to distribute swags at the recruiting event. We will put the swags into square-shaped boxes. Each box has to be completely filled so that the swags wouldn’t break during transportation. For example, a box can contain 1 swag, 4 swags, 9 swags, etc. (The boxes can be sufficiently large.)
 * However, if there are 10 swags, we have to put them into multiple boxes. For example, we could split them into four boxes:
 * Or maybe just two boxes are enough:
 * Given the number of swags, what is the minimum number of boxes to pack them up?
 *Example #1
 *
 * Input: 4
 *
 * Output: 1 (just one 2x2 box)
 *
 * Example #2
 *
 * Input: 10
 *
 * Output: 2 (one 3x3 box and one 1x1 box)
 * */
public class PackingUptheSwags {
    public int minBoxes(int num) {
        int[] M = new int [num + 1];
        Arrays.fill(M, Integer.MAX_VALUE);
        M[0] = 0;
        for (int i = 1; i <= num; i++) {
            for (int j = 1; j * j <= i; j++) { // j*j as 右小段
                M[i] = Math.min(M[i], M[i - j * j] + 1);
            }
        }
        return M[num];
    }
}
