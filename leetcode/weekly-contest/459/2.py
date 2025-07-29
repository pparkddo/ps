class Solution:

    def countTrapezoids(self, points: List[List[int]]) -> int:
        d = {}
        for x, y in points:
            d[y] = d.get(y, 0) + 1
        print(d)

        values = [v * (v-1) // 2 for v in d.values() if v > 1]
        print(values)

        n = len(values)
        if n <= 1:
            return 0

        prefix_sum = [values[0]]
        for i in range(1, n):
            prefix_sum.append(prefix_sum[i-1] + values[i])
        print(prefix_sum)

        answer = 0
        for i in range(n):
            answer += ((values[i] * (prefix_sum[-1] - prefix_sum[i])) % (10**9 + 7))
        return answer % (10**9 + 7)
