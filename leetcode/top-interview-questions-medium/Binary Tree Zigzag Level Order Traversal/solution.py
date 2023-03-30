from collections import deque

# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:

    def zigzagLevelOrder(self, root: TreeNode | None) -> list[list[int]]:
        answer: list[deque[int]] = []

        def dfs(node: TreeNode | None, depth: int) -> None:
            if not node:
                return

            if depth >= len(answer):
                answer.append(deque())

            if depth % 2 == 0:
                answer[depth].append(node.val)
            else:
                answer[depth].appendleft(node.val)

            dfs(node.left, depth+1)
            dfs(node.right, depth+1)

        dfs(root, 0)

        return answer
