# Definition for singly-linked list.
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next


class Solution:

    def oddEvenList(self, head: ListNode | None) -> ListNode | None:
        if not head:
            return head

        even_root: ListNode = ListNode()
        even_node: ListNode = even_root
        previous_node: ListNode
        current_node: ListNode = head
        next_node: ListNode = head.next
        is_even: bool = False

        while next_node:
            if is_even:
                even_node.next = current_node
                even_node = even_node.next
                even_node.next = None
            else:
                current_node.next = next_node.next

            previous_node = current_node
            current_node = next_node
            next_node = next_node.next
            is_even = not is_even

        if is_even:
            even_node.next = current_node
            even_node = even_node.next
            even_node.next = None
            previous_node.next = even_node.next
        else:
            current_node.next = even_root.next

        return head
