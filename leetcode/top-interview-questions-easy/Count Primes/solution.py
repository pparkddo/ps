class Solution:

    def countPrimes(self, n: int) -> int:
        def get_sieve(number):
            prime: list[bool] = [True for _ in range(number)]

            for i in range(min(2, number)):
                prime[i] = False

            p: int = 2

            while p * p <= number:
                if prime[p]:
                    for i in range(p * p, number, p):
                        prime[i] = False
                p += 1

            return prime

        sieve: list[bool] = get_sieve(n)

        return len(list(filter(lambda x: x, sieve)))
