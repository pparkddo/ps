class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:

    def sortedArrayToBST(self, nums: list[int]) -> TreeNode | None:

        def dfs(left: int, right: int) -> TreeNode | None:
            if left > right:
                return None

            if left == right:
                return TreeNode(nums[left])

            mid: int = (left + right) // 2
            num: int = nums[mid]
            return TreeNode(num, dfs(left, mid-1), dfs(mid+1, right))

        return dfs(0, len(nums)-1)


