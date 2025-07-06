class Solution:
    def processQueries(self, c: int, connections: List[List[int]], queries: List[List[int]]) -> List[int]:
        g = {}
        for a, b in connections:
            g.setdefault(a, []).append(b)
            g.setdefault(b, []).append(a)

        # print(g)

        def dfs(node: int, grid: list[int], visited: set[int]):
            grid.append(node)
            visited.add(node)

            for next_node in g.get(node, []):
                if next_node in visited:
                    continue
                dfs(next_node, grid, visited)

        visited = set()
        grids = []
        grid_id_by_node = {}
        grid_id = 0
        for i in range(1, c+1):
            if i in visited:
                continue
            grid = list()
            dfs(i, grid, visited)
            grid.sort()
            for node in grid:
                grid_id_by_node[node] = grid_id
            grid_id += 1
            grids.append(grid)

        # print(grids)
        # print(grid_id_by_node)

        offline_nodes = set()
        answer = []
        for command, node in queries:
            if command == 1:
                if node not in offline_nodes:
                    answer.append(node)
                    continue

                grid_id = grid_id_by_node[node]
                to_be_removed_index = -1
                grid = grids[grid_id]
                for each in grid:
                    if each not in offline_nodes:
                        break
                    to_be_removed_index += 1

                if to_be_removed_index != -1:
                    grid = grid[to_be_removed_index+1:]
                    grids[grid_id] = grid

                if grid:
                    answer.append(grid[0])
                else:
                    answer.append(-1)
            if command == 2:
                offline_nodes.add(node)

        return answer
    