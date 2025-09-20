import sys; input=sys.stdin.readline; sys.setrecursionlimit(10**6)
import math

# minimum matrix chain multiplication
def solution(matrix_sizes):
    n = len(matrix_sizes)
    dp = [[-1] * (n+1) for _ in range(n+1)]

    def rec(i, j):
        if i + 1 == j:
           return 0

        if dp[i][j] != -1:
            return dp[i][j]

        result = math.inf
        for k in range(i+1, j):
            v = rec(i, k) + rec(k, j) + matrix_sizes[i][0] * matrix_sizes[k-1][1] * matrix_sizes[j-1][1]
            result = min(result, v)
        dp[i][j] = result
        return result

    return rec(0, n)
