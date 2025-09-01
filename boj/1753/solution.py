import sys; input=sys.stdin.readline; sys.setrecursionlimit(10**6)
import math
import heapq


def main(v, e, k, graph):
    dist = [math.inf for _ in range(v + 1)]
    pq = []

    dist[k] = 0
    pq.append((0, k))

    while pq:
        _, v = heapq.heappop(pq)
        for u, w in graph.get(v, []):
            alt = dist[v] + w
            if alt < dist[u]:
                dist[u] = alt
                heapq.heappush(pq, (alt, u))

    return "\n".join([str(each) if each != math.inf else "INF" for each in dist[1:]])


if __name__ == '__main__':
    v, e = map(int, input().split())
    k = int(input())

    graph = {}
    for _ in range(e):
        u, v_, w = map(int, input().split())
        graph.setdefault(u, []).append((v_, w))

    print(main(v, e, k, graph))
