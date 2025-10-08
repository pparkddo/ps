import math


class Solution:

    def splitArray(self, nums: List[int]) -> int:
        n = len(nums)

        # valid_index_at_most
        left, right = 0, n - 1

        while left <= n - 2 and nums[left+1] > nums[left]:
            left += 1

        while right >= 1 and nums[right-1] > nums[right]:
            right -= 1

        sum_ = sum(nums)
        index = 0
        temp = 0
        answer = math.inf
        while index <= left:
            temp += nums[index]
            valid = ((index + 1) + (n - right)) >= n
            if valid:
                answer = min(answer, abs(sum_-temp-temp))
            index += 1

        return answer if answer != math.inf else -1
