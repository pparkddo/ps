class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution {
    
    private ListNode reverse(ListNode head) {
        ListNode node = new ListNode();
        while (head != null) {
            node.val = head.val;
            ListNode newNode = new ListNode();
            newNode.next = node;
            node = newNode;
            head = head.next;
        }
        return node.next;
    }
    
    public boolean isPalindrome(ListNode head) {
        ListNode reversed = reverse(head);
        while (head != null) {
            if (reversed.val != head.val) {
                return false;
            }
            reversed = reversed.next;
            head = head.next;
        }
        return true;
    }
}
