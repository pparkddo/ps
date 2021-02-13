import java.util.ArrayList;
import java.util.List;

public class Solution {

    private int getNullSafeValue(ListNode node) {
        return node != null ? node.val : 0;
    }

    private ListNode getNullSafeNext(ListNode node) {
        return node != null ? node.next : null;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode node = new ListNode();
        ListNode answer = node;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int sum = getNullSafeValue(l1) + getNullSafeValue(l2) + carry;
            carry = sum >= 10 ? 1 : 0;

            l1 = getNullSafeNext(l1);
            l2 = getNullSafeNext(l2);

            node.val = sum % 10;

            if (l1 == null && l2 == null) {
                break;
            }

            ListNode nextNode = new ListNode();
            node.next = nextNode;
            node = nextNode;
        }
        if (carry != 0) {
            ListNode nextNode = new ListNode();
            node.next = nextNode;
            nextNode.val = carry;
        }
        return answer;
    }
    
    public static void main(String[] args) {
        System.out.println(
            new Solution().addTwoNumbers(
                new ListNode(2, new ListNode(4, new ListNode(3))),
                new ListNode(5, new ListNode(6, new ListNode(4)))
            )
        );
        System.out.println(
            new Solution().addTwoNumbers(
                new ListNode(2),
                new ListNode(5)
            )
        );
        System.out.println(
            new Solution().addTwoNumbers(
                new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9))))))),
                new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9))))
            )
        );
        System.out.println(
            new Solution().addTwoNumbers(
                new ListNode(4, new ListNode(3, new ListNode(2, new ListNode(1)))),
                new ListNode(7, new ListNode(6, new ListNode(5, new ListNode(4, new ListNode(3, new ListNode(2, new ListNode(1)))))))
            )
        );
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    @Override
    public String toString() {
        List<Integer> l = new ArrayList<>();
        ListNode temp = this;
        while (temp != null) {
            l.add(temp.val);
            temp = temp.next;
        }
        return l.toString();
    }
}
