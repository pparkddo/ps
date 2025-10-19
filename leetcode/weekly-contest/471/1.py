from collections import Counter


class Solution:
    def sumDivisibleByK(self, nums: List[int], k: int) -> int:
        counter = Counter(nums)
        answer = 0

        for kk, v in counter.items():
            if v % k == 0:
                answer += (kk * v)

        return answer