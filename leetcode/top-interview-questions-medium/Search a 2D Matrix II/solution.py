class Solution:

    def searchMatrix(self, matrix: list[list[int]], target: int) -> bool:
        row_index: int = 0
        column_index: int = len(matrix[0]) - 1

        while row_index < len(matrix) and column_index >= 0:
            if target == matrix[row_index][column_index]:
                return True
            elif target < matrix[row_index][column_index]:
                column_index -= 1
            else:
                row_index += 1

        return False
