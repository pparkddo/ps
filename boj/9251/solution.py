import sys; input=sys.stdin.readline; sys.setrecursionlimit(10**6)


def main(s, t):
    dp = [[0 for _ in range(len(t)+1)] for _ in range(len(s)+1)]

    for i in range(1, len(s)+1):
        for j in range(1, len(t)+1):
            if s[i-1] == t[j-1]:
                dp[i][j] = dp[i-1][j-1] + 1
            else:
                dp[i][j] = max(dp[i-1][j], dp[i][j-1])

    return dp[len(s)][len(t)]


if __name__ == '__main__':
    s = input().strip()
    t = input().strip()
    print(main(s, t))
