"""
[10,9,2,5,3,7,101,18]
[0,1,0,3,2,3]
[7,7,7,7,7]
[1,4,2,3]
[4,10,4,3,8,9]
[3,5,6,2,5,4,19,5,6,7,12]
"""


# noinspection PyPep8Naming,DuplicatedCode,PyMethodMayBeStatic
class BruteForceSolution:

    def lengthOfLIS(self, nums: list[int]) -> int:
        answer: int = 1

        def traverse(index: int, subsequence_length: int, largest_number: int) -> int:
            result: int = subsequence_length

            for i in range(index + 1, len(nums)):
                next_number: int = nums[i]
                next_subsequence_length = subsequence_length + 1 if next_number > largest_number else subsequence_length
                result = max(result, traverse(i, next_subsequence_length, max(largest_number, next_number)))

            return result

        for index in range(len(nums)):
            answer = max(answer, traverse(index, 1, nums[index]))

        return answer


# noinspection PyPep8Naming,DuplicatedCode,PyMethodMayBeStatic
class AnotherBruteForceSolution:

    def lengthOfLIS(self, nums: list[int]) -> int:
        def traverse(index: int, largest_number: int) -> int:
            if index >= len(nums):
                return 0

            take: int = 0
            dont_take: int = traverse(index + 1, largest_number)

            if nums[index] > largest_number:
                take = 1 + traverse(index + 1, nums[index])

            return max(take, dont_take)

        return traverse(0, -2 ** 30)


# noinspection PyPep8Naming,DuplicatedCode,PyMethodMayBeStatic
class SimpleMemoizationSolution:

    def lengthOfLIS(self, nums: list[int]) -> int:
        dp: list[list[int]] = [[-1] * len(nums) for _ in range(len(nums))]

        def traverse(index: int, largest_number_index: int) -> int:
            if index >= len(nums):
                return 0

            if dp[index][largest_number_index] != -1:
                return dp[index][largest_number_index]

            take: int = 0
            dont_take: int = traverse(index + 1, largest_number_index)

            if largest_number_index == -1 or nums[index] > nums[largest_number_index]:
                take = 1 + traverse(index + 1, index)

            dp[index][largest_number_index] = max(take, dont_take)
            return dp[index][largest_number_index]

        return traverse(0, -1)


# noinspection PyPep8Naming,DuplicatedCode,PyMethodMayBeStatic
class TabulationSolution:

    def lengthOfLIS(self, nums: list[int]) -> int:
        if not nums:
            return 0

        n = len(nums)
        dp = [1] * n

        for i in range(1, n):
            for j in range(i):
                if nums[i] > nums[j]:
                    dp[i] = max(dp[i], 1 + dp[j])

        return max(dp)


# noinspection PyPep8Naming,DuplicatedCode,PyMethodMayBeStatic
# https://leetcode.com/explore/interview/card/top-interview-questions-medium/111/dynamic-programming/810/discuss/1326552/Optimization-From-Brute-Force-to-Dynamic-Programming-Explained!
class BinarySearchSolution:

    def lengthOfLIS(self, nums: list[int]) -> int:

        def binary_search(subsequence: list[int], value: int) -> int:
            left: int = 0
            right: int = len(subsequence) - 1

            while left <= right:
                mid: int = left + (right - left) // 2

                if subsequence[mid] < value:
                    left = mid + 1
                elif subsequence[mid] > value:
                    right = mid - 1
                else:
                    return mid

            return left

        subsequence: list[int] = []

        for num in nums:
            position = binary_search(subsequence, num)

            if position == len(subsequence):
                subsequence.append(num)
            else:
                subsequence[position] = num

        return len(subsequence)
