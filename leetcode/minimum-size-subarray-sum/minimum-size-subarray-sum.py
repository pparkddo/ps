import math


class Solution:

    def minSubArrayLen(self, target: int, nums: List[int]) -> int:
        left, right = 0, 0

        answer = math.inf
        sum_ = 0
        while right < len(nums):
            sum_ += nums[right]

            while sum_ >= target:
                answer = min(answer, right - left + 1)
                sum_ -= nums[left]
                left += 1

            right += 1

        return answer if answer != math.inf else 0
