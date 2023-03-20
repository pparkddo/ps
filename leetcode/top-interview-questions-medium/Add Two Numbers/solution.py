# Definition for singly-linked list.
from collections import deque


class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next


class Solution:

    def addTwoNumbers(self, l1: ListNode | None, l2: ListNode | None) -> ListNode | None:
        queue: deque[int] = deque()

        while l1 and l2:
            queue.append(l1.val + l2.val)
            l1 = l1.next
            l2 = l2.next

        while l1:
            queue.append(l1.val)
            l1 = l1.next

        while l2:
            queue.append(l2.val)
            l2 = l2.next

        answer: ListNode = ListNode()
        node: ListNode = answer

        carry: int = 0

        while queue:
            value: int = queue.popleft() + carry

            node.next = ListNode(value if value < 10 else value - 10)
            node = node.next

            carry = 1 if value >= 10 else 0

        if carry:
            node.next = ListNode(carry)

        return answer.next
