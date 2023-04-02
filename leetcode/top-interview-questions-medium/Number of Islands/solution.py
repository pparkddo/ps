class Solution:

    def numIslands(self, grid: list[list[str]]) -> int:
        answer: int = 0

        directions: list[list[int]] = [[-1, 0], [1, 0], [0, -1], [0, 1]]
        visited: list[list[bool]] = [[False] * len(grid[0]) for _ in range(len(grid))]

        def is_valid_indexes(row_index: int, column_index: int) -> bool:
            return 0 <= row_index < len(grid) and 0 <= column_index < len(grid[0])

        def dfs(row_index: int, column_index: int):
            value: str = grid[row_index][column_index]

            visited[row_index][column_index] = True

            if value == '1':
                for direction in directions:
                    next_row_index = row_index + direction[0]
                    next_column_index = column_index + direction[1]

                    if not is_valid_indexes(next_row_index, next_column_index):
                        continue

                    if not visited[next_row_index][next_column_index] and grid[next_row_index][next_column_index] == '1':
                        dfs(next_row_index, next_column_index)

        for row_index in range(len(grid)):
            for column_index in range(len(grid[row_index])):
                if not visited[row_index][column_index] and grid[row_index][column_index] == '1':
                    dfs(row_index, column_index)
                    answer += 1

        return answer
