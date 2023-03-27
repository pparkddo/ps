# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:

    def inorderTraversal(self, root: TreeNode | None) -> list[int]:
        answer: list = []

        def rec(node: TreeNode | None):
            if not node:
                return

            rec(node.left)
            answer.append(node.val)
            rec(node.right)

        rec(root)

        return answer
