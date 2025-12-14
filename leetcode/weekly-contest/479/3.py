import bisect


class Solution:
    def totalScore(self, hp: int, damage: List[int], requirement: List[int]) -> int:
        prefix_sum = [0]
        for d in damage:
            prefix_sum.append(prefix_sum[-1] + d)
        # print(prefix_sum)

        n = len(damage)
        answer = 0
        for j in range(n):
            required = requirement[j] + prefix_sum[j+1] - hp
            i = bisect.bisect_left(prefix_sum, required, 0, j+1)
            # print(required, i, (j - i + 1))
            answer += (j - i + 1)

        return answer