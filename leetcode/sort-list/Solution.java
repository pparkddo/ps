class Solution {

    public ListNode sortList(ListNode head) {
        ListNode dummy = new ListNode();
        dummy.next = head;

        int n = 0;
        while (head != null) {
            head = head.next;
            n++;
        }

        for (int step = 1; step < n; step <<= 1) {
            ListNode prev = dummy;
            ListNode cur = dummy.next;
            while (cur != null) {
                ListNode left = cur;
                ListNode right = split(left, step);
                cur = split(right, step);
                prev = merge(left, right, prev);
            }
        }

        return dummy.next;
    }

    private ListNode split(ListNode node, int step) {
        if (node == null) {
            return null;
        }

        for (int i = 1; node.next != null && i < step; i++) {
            node = node.next;
        }

        ListNode next = node.next;
        node.next = null;
        return next;
    }

    private ListNode merge(ListNode left, ListNode right, ListNode prev) {
        ListNode cur = prev;

        while (left != null && right != null) {
            if (left.val < right.val) {
                cur.next = left;
                left = left.next;
            } else {
                cur.next = right;
                right = right.next;
            }
            cur = cur.next;
        }

        if (left != null) {
            cur.next = left;
        }

        if (right!= null) {
            cur.next = right;
        }

        while (cur.next != null) {
            cur = cur.next;
        }

        return cur;
    }
}