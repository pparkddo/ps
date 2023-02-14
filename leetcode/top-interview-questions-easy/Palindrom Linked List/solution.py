import math


class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next


class Solution:

    def isPalindrome(self, head: ListNode | None) -> bool:
        node: ListNode | None = head
        count: int = 0

        while node:
            node = node.next
            count += 1

        half: int = math.ceil(count / 2)
        current_count: int = 0

        node = head

        while current_count < half:
            node = node.next
            current_count += 1

        reversed_: ListNode | None = None

        while node:
            current = node
            node = node.next
            current.next = reversed_
            reversed_ = current

        while reversed_:
            if head.val != reversed_.val:
                return False
            head = head.next
            reversed_ = reversed_.next

        return True
