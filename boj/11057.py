from collections import deque

MOD = 10007
number = int(input())

dp = list()
dp.append(deque([1] * 10))

for n in range(1, number):
    dp.append(deque())
    dp[n].appendleft(1)
    for d in range(9, 0, -1):
        dp[n].appendleft((dp[n][0] + dp[n-1][d-1]) % MOD)

print(sum(dp[number-1]) % MOD)
