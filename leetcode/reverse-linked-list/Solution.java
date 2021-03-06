class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode reversed = new ListNode();
        while (head != null) {
            reversed.val = head.val;
            ListNode previous = new ListNode();
            previous.next = reversed;
            reversed = previous;
            head = head.next;
        }
        return reversed.next;
    }
}
