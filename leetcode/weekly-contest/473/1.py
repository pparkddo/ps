class Solution:
    def removeZeros(self, n: int) -> int:
        return int("".join([i for i in str(n) if i != "0"]))
