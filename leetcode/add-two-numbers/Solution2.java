class Solution {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode answer = new ListNode();
        ListNode node = answer;

        boolean hasCarry = false;
        while (l1 != null && l2 != null) {
            int val = l1.val + l2.val + (hasCarry ? 1 : 0);
            hasCarry = val >= 10;
            node.next = new ListNode(val % 10);
            node = node.next;
            l1 = l1.next;
            l2 = l2.next;
        }

        while (l1 != null) {
            int val = l1.val + (hasCarry ? 1 : 0);
            hasCarry = val >= 10;
            node.next = new ListNode(val % 10);
            node = node.next;
            l1 = l1.next;
        }

        while (l2 != null) {
            int val = l2.val + (hasCarry ? 1 : 0);
            hasCarry = val >= 10;
            node.next = new ListNode(val % 10);
            node = node.next;
            l2 = l2.next;
        }

        if (hasCarry) {
            node.next = new ListNode(1);
        }

        return answer.next;
    }
}
