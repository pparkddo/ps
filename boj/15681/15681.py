import sys; input = sys.stdin.readline; sys.setrecursionlimit(10**6)


if __name__ == '__main__':
    n, r, q = map(int, input().split())

    graph = {}
    for _ in range(n-1):
        u, v = map(int, input().split())
        graph.setdefault(u, []).append(v)
        graph.setdefault(v, []).append(u)

    visited = [False for _ in range(n+1)]
    sizes = [0 for _ in range(n+1)]

    def count(u, graph, visited, sizes):
        sizes[u] = 1

        visited[u] = True
        for v in graph[u]:
            if not visited[v]:
                sizes[u] += count(v, graph, visited, sizes)

        return sizes[u]

    count(r, graph, visited, sizes)

    answer = []
    for _ in range(q):
        u = int(input())
        answer.append(sizes[u])

    print("\n".join(map(str, answer)))
