import sys; input=sys.stdin.readline; sys.setrecursionlimit(10**6)
from collections import defaultdict
from random import getrandbits


def generate_costs_and_parents_and_parent_costs(n, graph, colors):
    visited = [False] * (n+1)
    parents = [i for i in range(n+1)]
    parent_costs = [i for i in range(n+1)]
    costs = [defaultdict(int) for _ in range(n+1)]

    def dfs(v):
        visited[v] = True

        for u, c in graph[v]:
            if visited[u]:
                parents[v] = u
                continue
            dfs(u)
            parent_costs[u] = c
            costs[v][colors[u]^r] += parent_costs[u]

    dfs(1)

    return costs, parents, parent_costs


if __name__ == '__main__':
    r = getrandbits(32)
    for _ in range(int(input())):
        n, q = map(int, input().split())
        colors = list(map(int, ("-1 " + input()).split()))

        graph = [[] for _ in range(n+1)]
        sum_ = 0
        for _ in range(n-1):
            u, v, c = map(int, input().split())
            graph[u].append((v, c))
            graph[v].append((u, c))
            sum_ += c if colors[u] != colors[v] else 0

        costs, parents, parent_costs = generate_costs_and_parents_and_parent_costs(n, graph, colors)

        for _ in range(q):
            v, color = map(int, input().split())

            if v != 1:
                costs[parents[v]][colors[v]^r] -= parent_costs[v]
                if colors[parents[v]] == colors[v]:
                    sum_ += parent_costs[v]
                costs[parents[v]][color^r] += parent_costs[v]
                if colors[parents[v]] == color:
                    sum_ -= parent_costs[v]

            sum_ += costs[v][colors[v]^r]
            sum_ -= costs[v][color^r]
            colors[v] = color
            print(sum_)
