import math


class Solution:
    def minDifference(self, n: int, k: int) -> List[int]:
        divisors = [i for i in range(1, n + 1) if n % i == 0]
        # print(divisors)

        answer = []
        min_diff = math.inf

        def dfs(v, numbers, depth):
            nonlocal k, min_diff, answer
            if depth == k:
                if v == n and abs(numbers[-1] - numbers[0]) < min_diff:
                    min_diff = abs(numbers[-1] - numbers[0])
                    answer = numbers[:]
                return

            for d in divisors:
                if numbers and d < numbers[-1]:
                    continue
                if v * d > n:
                    break
                numbers.append(d)
                dfs(v*d, numbers, depth + 1)
                numbers.pop()

        dfs(1, [], 0)

        return answer
