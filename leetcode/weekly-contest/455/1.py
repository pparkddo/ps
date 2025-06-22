from collections import Counter


def get_prime(n):
    sieve = [True for _ in range(n)]

    for i in range(min(2, n)):
        sieve[i] = False

    i = 2

    while i * i <= n:
        if sieve[i]:
            for j in range(i * i, n, i):
                sieve[j] = False
        i += 1

    return sieve


class Solution:

    def checkPrimeFrequency(self, nums: List[int]) -> bool:
        counter = Counter(nums)
        prime = get_prime(101)

        for v in counter.values():
            if prime[v]:
                return True

        return False