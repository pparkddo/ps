# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:

    def __init__(self):
        self.k: int = -1
        self.found: bool = False
        self.answer: int = -1

    def kthSmallest(self, root: TreeNode | None, k: int) -> int:
        self.k = k
        self.found = False

        def traverse(node: TreeNode | None):
            if not node or self.found:
                return

            traverse(node.left)
            self.k -= 1

            if self.k == 0:
                self.found = True
                self.answer = node.val

            traverse(node.right)

        traverse(root)

        return self.answer
