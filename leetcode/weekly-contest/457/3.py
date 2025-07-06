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

    def minTime(self, n: int, edges: List[List[int]], k: int) -> int:
        g = {}
        for a, b, t in edges:
            g.setdefault(a, []).append((b, t))
            g.setdefault(b, []).append((a, t))
        # print(g)

        sorted_edges = sorted(edges, key=lambda x: x[2], reverse=True)
        # print(sorted_edges)

        def dfs(node, visited):
            visited.add(node)
            for next_node, _ in g.get(node, []):
                if next_node not in visited:
                    dfs(next_node, visited)

        visited = set()
        number_of_components = 0
        for i in range(n):
            if i in visited:
                continue
            dfs(i, visited)
            number_of_components += 1
        # print(number_of_components)

        if number_of_components >= k:
            return 0

        parents = [i for i in range(n)]
        count = n
        for a, b, t in sorted_edges:
            if union(a, b, parents):
                count -= 1
            if count < k:
                return t

        return 0
