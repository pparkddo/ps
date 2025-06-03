import heapq
import math
from typing import List


class Solution:

    def maxProbability(self, n: int, edges: List[List[int]], succProb: List[float], start_node: int, end_node: int) -> float:
        graph = {}
        for i, (u, v) in enumerate(edges):
            graph.setdefault(u, []).append((v, succProb[i]))
            graph.setdefault(v, []).append((u, succProb[i]))

        dist = [-math.inf for _ in range(n)]
        dist[start_node] = 1

        pq = [(-1.0, start_node)]

        while pq:
            p, node = heapq.heappop(pq)

            if node == end_node:
                return dist[node]

            for next_, next_p in graph.get(node, []):
                pp = -p * next_p
                if dist[next_] >= pp:
                    continue
                dist[next_] = pp
                heapq.heappush(pq, (-dist[next_], next_))

        return 0