# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:

    def isValidBST(self, root: TreeNode | None) -> bool:

        def dfs(node: TreeNode | None, min_: int | None, max_: int | None) -> bool:
            if not node:
                return True

            if min_ is not None and node.val <= min_:
                return False

            if max_ is not None and node.val >= max_:
                return False

            return dfs(node.left, min_, node.val) and dfs(node.right, node.val, max_)

        return dfs(root, None, None)
