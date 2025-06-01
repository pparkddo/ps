import math
import sys; input = sys.stdin.readline; sys.setrecursionlimit(10**6)


if __name__ == '__main__':
    n = int(input())
    graph = {}

    for _ in range(n-1):
        u, v = map(int, input().split())
        graph.setdefault(u, []).append(v)
        graph.setdefault(v, []).append(u)


    def traverse(n, graph, dp, visited):
        visited.add(n)

        dp[n][0] = 0
        dp[n][1] = 1

        for v in graph.get(n, []):
            if v in visited:
                continue
            traverse(v, graph, dp, visited)
            dp[n][0] += dp[v][1]
            dp[n][1] += min(dp[v][0], dp[v][1])


    dp = [[math.inf for _ in range(2)] for _ in range(n+1)]
    traverse(1, graph, dp, set())
    print(min(dp[1]))
