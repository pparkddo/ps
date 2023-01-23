# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
from collections import deque


class Solution:

    # def maxDepth(self, root: Optional[TreeNode]) -> int:
    def maxDepth(self, root) -> int:
        # to traverse all nodes, we can use 'bfs' or 'dfs'
        # here, I would like to use 'dfs'
        # since we have to track its depth
        # 'dfs' is way more than easier to keep its track
        # (in case of 'bfs', we need to deliver current depth as well as the node.)
        return self.dfs(root, 0)

    def dfs(self, node, depth) -> int:
        if not node:
            return depth

        return max(self.dfs(node.left, depth+1), self.dfs(node.right, depth+1))

