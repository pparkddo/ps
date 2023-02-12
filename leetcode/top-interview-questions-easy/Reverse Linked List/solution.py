# Definition for singly-linked list.
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next


class Solution:

    def reverseList(self, head: ListNode | None) -> ListNode | None:
        reversed_: ListNode | None = None
        node: ListNode | None = head

        while node:
            current = node
            node = node.next
            current.next = reversed_
            reversed_ = current

        return reversed_
