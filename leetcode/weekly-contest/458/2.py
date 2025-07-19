def union(a, b, parents):
    a, b = find(a, parents), find(b, parents)
    if a == b:
        return False
    parents[a] = b
    return True


def find(a, parents):
    if parents[a] == a:
        return a
    parents[a] = find(parents[a], parents)
    return parents[a]


class Solution:
    def minCost(self, n: int, edges: List[List[int]], k: int) -> int:
        sorted_edges = sorted(edges, key=lambda x: x[2])

        parents = [i for i in range(n)]
        components = n

        answer = None
        for a, b, w in sorted_edges:
            if union(a, b, parents):
                components -= 1
            if components == k:
                answer = w
        return answer