import sys; input=sys.stdin.readline; sys.setrecursionlimit(10**6)


def main(n, m, maze):
    dp = [[0] * (m+1) for _ in range(n+1)]

    for i in range(1, n+1):
        for j in range(1, m+1):
            dp[i][j] = max(dp[i-1][j], dp[i][j-1], dp[i-1][j-1]) + maze[i-1][j-1]

    return dp[-1][-1]


if __name__ == '__main__':
    n, m = map(int, input().split())
    maze = []
    for _ in range(n):
        maze.append(list(map(int, input().split())))
    print(main(n, m, maze))
