# Definition for a Node.
from collections import deque


class Node:
    def __init__(self, val: int = 0, left: 'Node' = None, right: 'Node' = None, next: 'Node' = None):
        self.val = val
        self.left = left
        self.right = right
        self.next = next


class Solution:

    def connect(self, root: Node | None) -> Node | None:
        queue: deque[tuple[Node | None, int]] = deque()

        queue.append((root, 0))
        previous: Node | None = None
        previous_depth: int = -1

        while queue:
            node, depth = queue.popleft()

            if depth == previous_depth and previous is not None:
                previous.next = node

            previous, previous_depth = node, depth

            if not node:
                continue

            queue.append((node.left, depth+1))
            queue.append((node.right, depth+1))

        return root
