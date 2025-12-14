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
    def largestPrime(self, n: int) -> int:
        if n < 2:
            return 0

        prime = get_prime(n+1)
        # print(prime)

        numbers = [0]
        for i in range(2, len(prime)):
            if not prime[i]:
                continue
            numbers.append(numbers[-1] + i)
        numbers = numbers[1:]
        numbers = list(filter(lambda x: x <= n and prime[x], numbers))
        # print(numbers)

        answer = 0
        for number in numbers:
            if number > n:
                break
            answer = number

        return answer