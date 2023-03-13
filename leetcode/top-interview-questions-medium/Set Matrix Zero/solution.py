class Solution:

    def setZeroes(self, matrix: list[list[int]]) -> None:
        """
        Do not return anything, modify matrix in-place instead.
        """
        rows_to_be_zero: set[int] = set()
        columns_to_be_zero: set[int] = set()

        for row_index, row in enumerate(matrix):
            for column_index, value in enumerate(row):
                if value == 0:
                    rows_to_be_zero.add(row_index)
                    columns_to_be_zero.add(column_index)

        for row_index in range(len(matrix)):
            for column_index in range(len(matrix[0])):
                if row_index in rows_to_be_zero or column_index in columns_to_be_zero:
                    matrix[row_index][column_index] = 0
