import sys
from collections import defaultdict, deque

input = sys.stdin.readline
sys.setrecursionlimit(10 ** 6)

NO = 0


class Solution:

    def __init__(self):
        n, m, r = map(int, input().split(" "))

        self.graph: dict[int, list[int]] = defaultdict(list)
        self.visited: list[int] = [NO] * (n + 1)
        self.seq: int = 1

        for _ in range(m):
            a, b = map(int, input().split(" "))
            self.graph[a].append(b)
            self.graph[b].append(a)

        for each_vertex in self.graph:
            self.graph[each_vertex].sort(reverse=True)

        self.bfs(r)

        for each_vertex in range(1, len(self.visited)):
            print(self.visited[each_vertex])

    def bfs(self, vertex: int):
        queue = deque[int]()

        queue.append(vertex)
        self.visited[vertex] = self.seq
        self.seq = self.seq + 1

        while queue:
            u: int = queue.popleft()
            for each in self.graph[u]:
                if not self.visited[each]:
                    queue.append(each)
                    self.visited[each] = self.seq
                    self.seq = self.seq + 1


if __name__ == '__main__':
    Solution()
