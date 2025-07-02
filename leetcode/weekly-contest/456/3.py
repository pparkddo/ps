import math


class Solution:
    def minXor(self, nums, k):
        n = len(nums)
        def dfs(i: int, k: int):
            print(i, k)
            if i == n and k == 0:
                return 0
            if i == n or k <= 0:
                return math.inf
            res, x = math.inf, 0
            for j in range(i, n):
                x ^= nums[j]
                if x < res:
                    res = min(res, max(x, dfs(j + 1, k - 1)))
            return res
        return dfs(0, k)
