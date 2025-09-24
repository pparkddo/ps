import sys; input=sys.stdin.readline; sys.setrecursionlimit(10**6)
import math


def main(n, w):
    mask = (1 << n) - 1
    dp = [[-1] * (mask+1) for _ in range(n+1)]

    def dfs(v, visited):
        if visited == mask:
            return w[v][0] if w[v][0] else math.inf

        if dp[v][visited] != -1:
            return dp[v][visited]

        result = math.inf
        for next_v, c in enumerate(w[v]):
            if c == 0:
                continue
            if visited & (1 << next_v):
                continue
            result = min(result, dfs(next_v, visited | (1 << next_v)) + c)
        dp[v][visited] = result
        return result

    return dfs(0, 1)


if __name__ == '__main__':
    n = int(input())
    w = [list(map(int, input().split())) for _ in range(n)]
    print(main(n, w))
