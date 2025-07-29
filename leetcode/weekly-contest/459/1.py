class Solution:
    def checkDivisibility(self, n: int) -> bool:
        s = str(n)

        a, b = 0, 1
        for each in s:
            v = int(each)
            a += v
            b *= v

        # print(a, b, a + b, n % (a + b))

        return n % (a + b) == 0
