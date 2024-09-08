class Solution {

    public ListNode partition(ListNode head, int x) {
        ListNode leftPartHead = new ListNode();
        ListNode rightPartHead = new ListNode();

        ListNode leftPart = leftPartHead;
        ListNode rightPart = rightPartHead;

        while (head != null) {
            ListNode next = head.next;
            head.next = null;

            if (head.val < x) {
                leftPart.next = head;
                leftPart = leftPart.next;
            } else {
                rightPart.next = head;
                rightPart = rightPart.next;
            }

            head = next;
        }

        leftPart.next = rightPartHead.next;
        return leftPartHead.next;
    }
}
