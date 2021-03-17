package CrossExercise2;
import java.util.HashMap;
import java.util.Map;
/**
 * Each of the nodes in the linked list has another pointer pointing to a random node in the list or null.
 * Make a deep copy of the original list.
 */

class RandomListNode {
    public int value;
    public RandomListNode next;
    public RandomListNode random;

    public RandomListNode(int value) {
        this.value = value;
    }
}

public class DeepCopyLinkedListWithRandomPointer {
    public RandomListNode copy(RandomListNode head) {
        // Write your solution here.
        if (head == null) {
            return null;
        }

        Map<RandomListNode, RandomListNode> map = new HashMap<>();
        RandomListNode newHead = new RandomListNode(head.value);
        map.put(head, newHead);
        RandomListNode cur = newHead;

        while (head != null) {
            // step 1: copy node without random link
            if (head.next != null) {
                if (!map.containsKey(head.next)) {
                    map.put(head.next, new RandomListNode(head.next.value));
                }
                cur.next = map.get(head.next); // link cur node with cur.next . important step
            }
            // step 2 : copy random link
            if (head.random != null) {
                if (!map.containsKey(head.random)) {
                    map.put(head.random, new RandomListNode(head.random.value));
                }
                cur.random = map.get(head.random); // link cur node with cur.random.
            }
            head = head.next;
            cur = cur.next;
        }
        return newHead;
    }
}
