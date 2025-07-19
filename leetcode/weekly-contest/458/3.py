class Solution:
    def processStr(self, s: str, k: int) -> str:
        length = 0
        for c in s:
            if c.isalpha():
                length += 1
            elif c == "*":
                length = max(0, length - 1)
            elif c == "#":
                length *= 2

        if k >= length:
            return "."

        n = len(s)
        for i in range(n-1, -1, -1):
            c = s[i]
            if c.isalpha():
                if k == length - 1:
                    return c
                length -= 1
            elif c == "*":
                length += 1
            elif c == "#":
                length //= 2
                if k >= length:
                    k -= length
            elif c == "%":
                k = length - (k + 1)

        return "."