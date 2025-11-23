class Solution:
    def countDistinct(self, n: int) -> int:
        s = str(n)
        l = len(s)

        answer = 0
        for i in range(1, l):
            answer += 9 ** i

        for i in range(l):
            d = int(s[i])
            if d == 0:
                break
            remaining = l - i - 1
            combinations = 9 ** remaining
            answer += (d - 1) * combinations

        if "0" not in s:
            answer += 1

        return answer