class Solution:

    def sortColors(self, nums: list[int]) -> None:
        counts: list[int] = [0, 0, 0]

        for num in nums:
            counts[num] += 1

        count_index: int = 0
        for index in range(len(nums)):
            while counts[count_index] == 0:
                count_index += 1

            nums[index] = count_index
            counts[count_index] -= 1
