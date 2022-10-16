from typing import Optional


# Definition for singly-linked list.
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next


class Solution:

    def removeNthFromEnd(self, head: Optional[ListNode], n: int) -> Optional[ListNode]:
        size = 1 if head.next else 0
        node = head
        while node.next:
            node = node.next
            size += 1

        count = size
        node = head
        previous, next_ = None, None
        while node:
            if count == n+1:
                previous = node
            elif count == n-1:
                next_ = node
            node = node.next
            count -= 1

        if previous:
            previous.next = next_
        else:
            head = head.next

        return head
