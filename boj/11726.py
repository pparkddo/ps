number = int(input())

dp = list()

dp.append(1)
dp.append(1)

for n in range(2, number+1):
    dp.append((dp[n-1] + dp[n-2]) % 10007)

print(dp[number])
