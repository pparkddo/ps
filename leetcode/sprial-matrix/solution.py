from typing import List


class Solution:

    def spiralOrder(self, matrix: List[List[int]]) -> List[int]:
        m, n = len(matrix), len(matrix[0])
        row_count, column_count = m-1, n
        direction = 1

        result: List[int] = []
        x, y = -1, 0

        while True:
            for _ in range(column_count):
                x = x + direction
                result.append(matrix[y][x])

            column_count = column_count - 1

            if len(result) == m * n:
                break

            for _ in range(row_count):
                y = y + direction
                result.append(matrix[y][x])

            row_count = row_count - 1

            direction = direction * -1

        return result


if __name__ == '__main__':
    print(Solution().spiralOrder([[1,2,3],[4,5,6],[7,8,9]]))
    print(Solution().spiralOrder([[1,2,3,4],[5,6,7,8],[9,10,11,12]]))
    print(Solution().spiralOrder([[1,2,3,4]]))
    print(Solution().spiralOrder([[1,2,3,4,5,6,7,8], [9,10,11,12,13,14,15,16]]))
