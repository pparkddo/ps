number = int(input())

dp = []
dp.append([0, 1])

for n in range(1, number):
    dp.append([])
    dp[n].append(sum(dp[n-1]))  # 0
    dp[n].append(dp[n-1][0])  # 1

print(sum(dp[number-1]))
