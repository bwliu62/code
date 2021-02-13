package recursion2;
/*
Reverse pairs of elements in a singly-linked list.

Examples:

L = null, after reverse is null
L = 1 -> null, after reverse is 1 -> null
L = 1 -> 2 -> null, after reverse is 2 -> 1 -> null
L = 1 -> 2 -> 3 -> null, after reverse is 2 -> 1 -> 3 -> null
* **/
public class ReverseLinkedListInPairs {
    static class ListNode {
        private int value;
        private ListNode next;

        public ListNode(int value) {
            this.value = value;
            next = null;
        }
    }

    public ListNode reverseInPairs(ListNode head) {
        // Write your solution here
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = head.next;
        head.next = reverseInPairs(head.next.next);
        newHead.next = head;

        return newHead;
    }
}
