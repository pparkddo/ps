class Solution:
    def findGoodIntegers(self, n: int) -> list[int]:
        tripled = [i ** 3 for i in range(1, int(n ** (1/3)) + 1)]
        # print(tripled)

        count = {}
        for i in range(len(tripled)):
            for j in range(i+1, len(tripled)):
                a, b = tripled[i], tripled[j]
                if a + b <= n:
                    count[a+b] = count.get(a+b, 0) + 1
        # print(count)

        return sorted([number for number, c in count.items() if c >= 2])
