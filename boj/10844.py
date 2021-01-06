MOD = 1000000000
DIGIT = list(range(0, 10))
number = int(input())

dp = list()
dp.append([0] + [1] * 9)

for n in range(1, number):
    dp.append([])
    for d in DIGIT:
        if d == 0:
            dp[n].append(dp[n-1][1] % MOD)
        elif d == 9:
            dp[n].append(dp[n-1][8] % MOD)
        else:
            dp[n].append((dp[n-1][d-1] + dp[n-1][d+1]) % MOD)
    
print(sum(dp[number-1]) % MOD)
