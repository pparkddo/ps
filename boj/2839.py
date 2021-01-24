n = int(input())
dp = [None, None, 1, None, 1]
for index in range(5, n):
    value = min((each for each in (dp[index-3], dp[index-5]) if each), default=None)
    dp.append(value + 1 if value else None)
print(dp[n-1] if dp[n-1] else -1)
