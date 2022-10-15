import sys;

input = sys.stdin.readline
sys.setrecursionlimit(10 ** 6)

from typing import Dict, List
from collections import defaultdict

NOT_VISITED = 0


class Solution:

    def __init__(self, n, graph):
        self.seq = 1
        self.graph = graph
        self.visited = [NOT_VISITED] * (n + 1)
        self._sort_graph()

    def _sort_graph(self):
        for each in self.graph:
            self.graph[each].sort()

    def solution(self, r):
        self.dfs(r)

        for each in range(1, len(self.visited)):
            print(self.visited[each])

    def dfs(self, vertex):
        self.visited[vertex] = self.seq
        self.seq += 1

        for each in self.graph[vertex]:
            if self.visited[each] == NOT_VISITED:
                self.dfs(each)


if __name__ == '__main__':
    n, m, r = map(int, input().split())

    graph: Dict[int, List[int]] = defaultdict(list)

    for _ in range(m):
        u, v = map(int, input().split())
        graph[u].append(v)
        graph[v].append(u)

    Solution(n, graph).solution(r)
