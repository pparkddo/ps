from collections import deque


class Solution:
    def maximumMedianSum(self, nums: List[int]) -> int:
        nums.sort()

        answer = 0

        queue = deque(nums)

        while queue:
            queue.popleft()
            queue.pop()
            answer += queue.pop()

        return answer
    