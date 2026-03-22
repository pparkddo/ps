from typing import List


class Solution:
    def minRemovals(self, nums: List[int], target: int) -> int:
        n = len(nums)
        dp = [{} for _ in range(n)]

        def dfs(i: int, v: int):
            nonlocal n
            if i >= n:
               return 0 if v == target else 100
            
            if v in dp[i]:
                return dp[i][v]
            
            take = dfs(i+1, v^nums[i])
            not_take = 1 + dfs(i+1, v)
            dp[i][v] = min(take, not_take)

            return dp[i][v]
        
        answer = dfs(0, 0)
        return answer if answer <= n else -1
