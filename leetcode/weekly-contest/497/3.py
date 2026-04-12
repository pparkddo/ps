import bisect
from collections import defaultdict


class Solution:
    def longestBalanced(self, s: str) -> int:
        n = len(s)
        total_z = s.count('0')
        total_o = s.count('1')
        if min(total_z, total_o) == 0:
            return 0

        d = [0] * (n + 1)
        for i in range(n):
            d[i+1] = d[i] + (1 if s[i] == '0' else -1)

        occ = defaultdict(list)
        first = {}
        for i in range(n + 1):
            v = d[i]
            if v not in first:
                first[v] = i
            occ[v].append(i)

        answer = 0
        for R in range(1, n + 1):
            v = d[R]
            # Case 1: balanced (z == o)
            answer = max(answer, R - first[v])
            # Case 2/3: |z - o| == 2, swap with spare character outside
            for delta, limit in ((-2, 2 * total_o), (2, 2 * total_z)):
                target = v + delta
                if target in occ:
                    lo = R - limit
                    idx = bisect.bisect_left(occ[target], lo)
                    if idx < len(occ[target]) and occ[target][idx] < R:
                        answer = max(answer, R - occ[target][idx])

        return answer
