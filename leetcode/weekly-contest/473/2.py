class Solution:
    def maxAlternatingSum(self, nums: List[int]) -> int:
        doubled = sorted([n ** 2 for n in nums])
        answer = 0

        half = len(doubled) // 2
        for i, d in enumerate(doubled):
            if i < half:
                answer -= d
            else:
                answer += d

        return answer
