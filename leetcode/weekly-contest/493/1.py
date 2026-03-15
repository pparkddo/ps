class Solution:
    def countCommas(self, n: int) -> int:
        answer = 0
        thousand = 10 ** 3
        for _ in range(thousand, n+1):
            answer += 1
        return answer
