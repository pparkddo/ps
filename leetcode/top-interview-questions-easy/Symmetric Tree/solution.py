class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:

    def isSymmetric(self, root: TreeNode | None) -> bool:
        def dfs(left: TreeNode | None, right: TreeNode | None):
            if left is None and right is None:
                return True

            if left is None or right is None or left.val != right.val:
                return False

            return dfs(left.right, right.left) and dfs(left.left, right.right)

        return dfs(root.left, root.right)
