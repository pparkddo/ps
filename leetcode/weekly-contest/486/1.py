class Solution:
    def minimumPrefixLength(self, nums: List[int]) -> int:
        stack = [nums[0]]

        answer = 0
        for num in nums[1:]:
            if stack[-1] >= num:
                answer += len(stack)
                stack = []
            stack.append(num)

        return answer
