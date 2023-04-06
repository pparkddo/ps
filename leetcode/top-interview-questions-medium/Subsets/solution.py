class Solution:

    def subsets(self, nums: list[int]) -> list[list[int]]:
        answer: list[list[int]] = []

        def traverse(index: int, depth: int, path: list[int]):
            answer.append(path)

            if depth == len(nums):
                return

            for i in range(index, len(nums)):
                traverse(i+1, depth + 1, path + [nums[i]])

        traverse(0, 0, [])

        return answer
