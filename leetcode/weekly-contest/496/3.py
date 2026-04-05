from typing import List


class Solution:
    def minIncrease(self, nums: List[int]) -> int:
        n = len(nums)
        cost = lambda i: max(0, max(nums[i - 1], nums[i + 1]) + 1 - nums[i])

        # (count, -cost) so tuple max maximizes count then minimizes cost
        not_sel = (0, 0)
        sel = (-1, 0)

        for i in range(1, n - 1):
            c = cost(i)
            new_not_sel = max(not_sel, sel)
            new_sel = (not_sel[0] + 1, not_sel[1] - c)
            not_sel, sel = new_not_sel, new_sel

        best = max(not_sel, sel)
        return -best[1]
