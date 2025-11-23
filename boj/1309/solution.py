import sys; input=sys.stdin.readline; sys.setrecursionlimit(10**6)


def main(n):
    dp = [1, 3]

    for i in range(2, n + 1):
        dp.append((dp[i - 1] * 2 + dp[i - 2]) % 9901)

    return dp[n]



if __name__ == '__main__':
    n = int(input())
    print(main(n))
