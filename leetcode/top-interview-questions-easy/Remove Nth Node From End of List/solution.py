# Definition for singly-linked list.
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next


class Solution:

    def removeNthFromEnd(self, head: ListNode | None, n: int) -> ListNode | None:
        # maintain two pointers and delay one with n steps
        node: ListNode = head
        delayed: ListNode | None = None

        # assign delayed_count to 1
        delayed_count: int = 1

        while node.next:
            node = node.next

            # if delayed_count becomes same as n,
            # then, move delayed pointer forward
            if n == delayed_count:
                delayed = delayed.next if delayed is not None else head
            else:
                delayed_count += 1

        # 'delayed' points at a node just before the node to be removed
        # if 'delayed' is None, the node to be removed is the head. therefore, delete the first node.
        if delayed is None:
            head = head.next
        # remove the node after the delayed node.
        else:
            node_to_be_removed = delayed.next
            delayed.next = node_to_be_removed.next

        return head
