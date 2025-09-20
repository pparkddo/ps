class Solution:
    def earliestTime(self, tasks: List[List[int]]) -> int:
        return min([a + b for a, b in tasks])