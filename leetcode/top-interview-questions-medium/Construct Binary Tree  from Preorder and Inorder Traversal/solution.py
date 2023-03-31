# Definition for a binary tree node.
from collections import deque


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:

    def buildTree(self, preorder: list[int], inorder: list[int]) -> TreeNode | None:
        inorder_index_dict: dict[int, int] = {value: index for index, value in enumerate(inorder)}

        preorder_deque: deque[int] = deque(preorder)

        def construct(start_index: int, end_index: int) -> TreeNode | None:
            if start_index > end_index:
                return
            value: int = preorder_deque.popleft()
            node: TreeNode = TreeNode(value)
            node_index: int = inorder_index_dict[value]
            node.left = construct(start_index, node_index - 1)
            node.right = construct(node_index + 1, end_index)
            return node

        return construct(0, len(preorder) - 1)
