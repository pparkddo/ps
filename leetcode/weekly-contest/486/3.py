from collections import defaultdict


class Solution:
    def specialNodes(self, n: int, edges: List[List[int]], x: int, y: int, z: int) -> int:
        tree = defaultdict(list)
        for a, b in edges:
            tree[a].append(b)
            tree[b].append(a)
        # print(tree)
        
        levels = {}
        visited = [False] * n
        def generate_level(node: int, depth: int):
            levels[node] = depth
            visited[node] = True
            for next_node in tree[node]:
                if visited[next_node]:
                    continue
                generate_level(next_node, depth + 1)
        root = 0
        generate_level(root, 0)
        # print(levels)

        def generate_distance(distance: dict[int, int], visited: list[bool], node: int, depth: int):
            nonlocal root
            distance[node] = depth
            visited[node] = True
            for next_node in tree[node]:
                if visited[next_node] or next_node == root:
                    continue
                generate_distance(distance, visited, next_node, depth + 1)
        
        distances = {}
        distance_x = {}
        generate_distance(distance_x, [False] * n, x, 0)
        distances[x] = distance_x
        
        distance_y = {}
        generate_distance(distance_y, [False] * n, y, 0)
        distances[y] = distance_y
        
        distance_z = {}
        generate_distance(distance_z, [False] * n, z, 0)
        distances[z] = distance_z
        # print(distances)

        answer = 0
        def cal_d(node: int, target: int) -> int:
            nonlocal root
            if node in distances[target]:
                return distances[target][node]
            return levels[node] + levels[target]
                
        for node in range(n):
            d = sorted([cal_d(node, x), cal_d(node, y), cal_d(node, z)])
            if d[0] ** 2 + d[1] ** 2 == d[2] ** 2:
                answer += 1
            
        return answer
