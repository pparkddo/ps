from collections import deque


class Solution:
    def colorGrid(self, n: int, m: int, sources: list[list[int]]) -> list[list[int]]:
        time: list[list[tuple[int, int]]] = [[(0, 999_999)] * m for _ in range(n)]
        matrix: list[list[int]] = [[0] * m for _ in range(n)]

        queue: deque[tuple[int, int, tuple[int, int]]] = deque()
        for r, c, color in sources:
            v = (color, 0)
            queue.append((r, c, v))
            time[r][c] = v
        # print(time)
        # print(queue)

        dirs = ((-1, 0), (1, 0), (0, -1), (0, 1)) 

        def is_available(r, c, n, m):
            return 0 <= r < n and 0 <= c < m
    
        while queue:
            # print(queue)
            r, c, (color, at) = queue.popleft()
            if at > time[r][c][1]:
                continue

            # < or <=
            if color <= matrix[r][c]:
                continue

            # print(r, c, (color, at))
            matrix[r][c] = color
            time[r][c] = (color, at)

            for d in dirs:
                nr, nc = r + d[0], c + d[1]
                if not is_available(nr, nc, n, m):
                    continue
                queue.append((nr, nc, (color, at+1)))


        return matrix
    