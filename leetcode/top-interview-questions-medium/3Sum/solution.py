class Solution:

    def threeSum(self, nums: list[int]) -> list[list[int]]:
        nums.sort()

        answer: list[list[int]] = []

        for index in range(len(nums)):
            if index > 0 and nums[index] == nums[index-1]:
                continue

            left_index: int = index+1
            right_index: int = len(nums) - 1

            num: int = nums[index]
            target: int = -num

            while left_index < right_index:
                left: int = nums[left_index]
                right: int = nums[right_index]

                if left + right == target:
                    answer.append([num, left, right])
                    left_index += 1
                    while left_index < right_index and nums[left_index] == nums[left_index-1]:
                        left_index += 1

                elif left + right < target:
                    left_index += 1

                else:
                    right_index -= 1

        return answer
