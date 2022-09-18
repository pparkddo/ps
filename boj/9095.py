def main(number):
    dp = []

    dp.append(1)
    dp.append(2)
    dp.append(4)

    for n in range(3, number):
        dp.append(dp[n-3]+dp[n-2]+dp[n-1])
    
    return dp[number-1]

for _ in range(int(input())):
    number = int(input())
    print(main(number))
