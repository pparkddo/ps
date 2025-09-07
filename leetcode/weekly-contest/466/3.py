class Solution:
    def bowlSubarrays(self, nums: List[int]) -> int:
        stack = []

        answer = 0

        for num in nums:
            while stack and stack[-1] < num:
                stack.pop()
                if stack:
                    answer += 1
            stack.append(num)

        return answer
