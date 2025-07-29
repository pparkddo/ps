import math
from collections import deque


def get_smallest_prime_factor(n):
    spf = [0 for _ in range(n)]

    for i in range(min(2, n)):
        spf[i] = i

    for i in range(2, n):
        if spf[i] == 0:
            spf[i] = i
            for j in range(i * i, n, i):
                spf[j] = i

    return spf


# def get_prime(n):
#     sieve = [True for _ in range(n)]
#
#     for i in range(min(2, n)):
#         sieve[i] = False
#
#     for i in range(2, n):
#         if sieve[i]:
#             for j in range(i * i, n, i):
#                 sieve[j] = False
#
#     return sieve

class Solution:
    def minJumps(self, nums: List[int]) -> int:
        n = len(nums)
        max_ = max(nums) + 1

        spf = get_smallest_prime_factor(max_)
        # print(spf)

        prime_graph = {}
        for i, num in enumerate(nums):
            factors = set()
            while num > 1:
                factor = spf[num]
                factors.add(factor)
                while num % factor == 0:
                    num //= factor
            for factor in factors:
                prime_graph.setdefault(factor, []).append(i)
        # print(prime_graph)

        dp = [math.inf for _ in range(n)]
        queue = deque()

        queue.append(0)
        dp[0] = 0

        while queue:
            i = queue.popleft()
            jump = dp[i]
            if i == n - 1:
                continue

            if spf[nums[i]] == nums[i]:
                for next_index in prime_graph.get(nums[i], []):
                    if dp[next_index] <= jump + 1:
                        continue
                    queue.append(next_index)
                    dp[next_index] = jump + 1

            if i > 0 and dp[i-1] > jump + 1:
                queue.append(i-1)
                dp[i-1] = jump + 1

            if dp[i+1] > jump + 1:
                queue.append(i+1)
                dp[i+1] = jump + 1

        return dp[n-1]
