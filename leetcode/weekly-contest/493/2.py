class Solution:
    def countCommas(self, n: int) -> int:
        answer = 0

        if n == 10 ** 15:
            answer += 15 // 3

        if n >= 10 ** 12:
            r = min(10**15 - 1, n) - 10 ** 12 + 1
            answer += r * (12 // 3)

        if n >= 10 ** 9:
            r = min(10**12 - 1, n) - 10 ** 9 + 1
            answer += r * (9 // 3)

        if n >= 10 ** 6:
            r = min(10**9 - 1, n) - 10 ** 6 + 1
            answer += r * (6 // 3)

        if n >= 10 ** 3:
            r = min(10**6 - 1, n) - 10 ** 3 + 1
            answer += r * (3 // 3)

        return answer
