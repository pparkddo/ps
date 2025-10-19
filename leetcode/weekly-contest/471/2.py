from collections import defaultdict


class Solution:
    def longestBalanced(self, s: str) -> int:
        n = len(s)

        answer = 0
        for i in range(n):
            d = defaultdict(int)
            for j in range(i, n):
                d[s[j]] += 1

                vv = d[s[j]]
                if all([v == vv for v in d.values()]):
                    answer = max(answer, j - i + 1)

        return answer