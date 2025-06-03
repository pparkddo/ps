import math


class Solution:

    def minSubArrayLen(self, target: int, nums: List[int]) -> int:
        prefix_sum = [nums[0]]
        for i in range(1, len(nums)):
            prefix_sum.append(prefix_sum[i - 1] + nums[i])

        def binary_search(l, r, t, prefix_sum):
            while l <= r:
                m = (l + r) // 2
                v = prefix_sum[m]
                if v < t:
                    l = m + 1
                else:
                    r = m - 1
            return l

        answer = math.inf
        for i in range(len(nums)):
            t = target + prefix_sum[i] - nums[i]
            r = binary_search(i, len(nums) - 1, t, prefix_sum)
            if r != -1 and r < len(nums):
                answer = min(answer, r - i + 1)
        return answer if answer != math.inf else 0

