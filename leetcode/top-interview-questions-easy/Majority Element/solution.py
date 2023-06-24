class Solution:

    def majorityElement(self, nums: list[int]) -> int:
        count: int = 0
        majority: int = nums[0]

        for num in nums:
            if count == 0:
                majority = num
                count += 1
                continue
            elif num == majority:
                count += 1
            elif num != majority:
                count -= 1

        return majority
