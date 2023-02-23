class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:

    def levelOrder(self, root: TreeNode | None) -> list[list[int]]:
        if root is None:
            return []

        answer: list[list[int]] = [[]]

        def dfs(node: TreeNode | None, depth: int) -> None:
            if node is None:
                return

            if len(answer) <= depth:
                answer.append([])

            dfs(node.left, depth + 1)
            dfs(node.right, depth + 1)

            answer[depth].append(node.val)

        dfs(root, 0)

        return answer
