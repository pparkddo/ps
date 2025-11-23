class Solution:
    def sumAndMultiply(self, n: int) -> int:
        s = "".join([x for x in str(n) if x != "0"])
        if not s:
            return 0
        return sum([int(x) for x in s]) * int(s)
