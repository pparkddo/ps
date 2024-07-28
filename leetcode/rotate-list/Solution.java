class Solution {

    public ListNode rotateRight(ListNode head, int k) {
        int count = 0;
        ListNode node = head;
        ListNode tail = null;
        while (node != null) {
            tail = node;
            node = node.next;
            count++;
        }

        if (count == 0) {
            return head;
        }

        int n = count - (k % count);
        if (n == 0) {
            return head;
        }

        ListNode newTail = node;
        node = head;
        while (n-- > 0) {
            newTail = node;
            node = node.next;
        }

        tail.next = head;
        ListNode newHead = newTail.next;
        newTail.next = null;

        return newHead;
    }
}
