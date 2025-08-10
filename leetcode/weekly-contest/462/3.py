from collections import defaultdict


class Solution:
    def maxTotal(self, value: List[int], limit: List[int]) -> int:
        d = defaultdict(list)
        for l, v in zip(limit, value):
            d[l].append(v)
        print(d)

        answer = 0
        for l, values in d.items():
            answer += sum(sorted(values, reverse=True)[:l])
        return answer
