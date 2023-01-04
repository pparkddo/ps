import sys
from collections import defaultdict

input = sys.stdin.readline
sys.setrecursionlimit(10 ** 6)

NO = 0
YES = 1


class Solution:

    def __init__(self):
        n, m, r = map(int, input().split(" "))

        self.graph: dict[int, list[int]] = defaultdict(list)
        self.visited: list[int] = [NO] * (n + 1)
        self.seq = 1

        for _ in range(m):
            a, b = map(int, input().split(" "))
            self.graph[a].append(b)
            self.graph[b].append(a)

        for each_vertex in self.graph:
            self.graph[each_vertex].sort(reverse=True)

        self.dfs(r)

        for each_vertex in range(1, len(self.visited)):
            print(self.visited[each_vertex])

    def dfs(self, vertex):
        self.visited[vertex] = self.seq
        self.seq += 1

        for each_vertex in self.graph[vertex]:
            if not self.visited[each_vertex]:
                self.dfs(each_vertex)


if __name__ == '__main__':
    Solution()
