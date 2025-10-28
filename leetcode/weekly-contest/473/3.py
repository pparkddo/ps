from collections import defaultdict


class Solution:
    def countStableSubarrays(self, capacity: List[int]) -> int:
        prefix = 0
        answer = 0

        d = defaultdict(int)  # maps (c, pre) to count
        for i, c in enumerate(capacity):
            prefix += c
            answer += d[(c, prefix-2*c)]
            d[(c, prefix)] += 1
            if c == 0 and i > 0 and capacity[i-1] == 0:
                answer -= 1

        return answer
