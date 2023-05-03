class Solution:

    def canJump(self, nums: list[int]) -> bool:
        max_index_that_can_be_visit: int = 0

        for index, num in enumerate(nums[:-1]):
            max_index_that_can_be_visit = max(index + num, max_index_that_can_be_visit)
            if max_index_that_can_be_visit == index:
                break

        return max_index_that_can_be_visit >= len(nums) - 1
