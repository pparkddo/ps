class Solution:

    def searchRange(self, nums: list[int], target: int) -> list[int]:
        starting_position: int = -1
        ending_position: int = -1

        left: int = 0
        right: int = len(nums) - 1

        while left <= right:
            mid: int = (left + right) // 2

            if nums[mid] == target:
                starting_position = mid
                right = mid - 1
            elif nums[mid] < target:
                left = mid + 1
            else:
                right = mid - 1

        left: int = 0
        right: int = len(nums) - 1

        while left <= right:
            mid: int = (left + right) // 2

            if nums[mid] == target:
                ending_position = mid
                right = mid - 1
            elif nums[mid] > target:
                right = mid - 1
            else:
                left = mid + 1

        return [starting_position, ending_position]
