from collections import deque


class Solution:
    def mergeAdjacent(self, nums: List[int]) -> List[int]:
        queue = deque(nums)
        answer = []

        while queue:
            if answer and answer[-1] == queue[0]:
                queue.appendleft(answer.pop() + queue.popleft())
                continue
            answer.append(queue.popleft())

        return answer
