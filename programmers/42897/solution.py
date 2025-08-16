import sys; sys.setrecursionlimit(10**6)


def solution(money):
    if len(money) <= 2:
        return max(money)

    def dfs(index, base_index, dp):
        if index == base_index:
            return money[base_index]
        if index == base_index - 1:
            return 0
        if index in dp:
            return dp[index]
        dp[index] = max(dfs(index-2, base_index, dp)+money[index], dfs(index-1, base_index, dp))
        return dp[index]

    n = len(money)
    return max(dfs(n-1, 1, {}), dfs(n-2, 0, {}))
