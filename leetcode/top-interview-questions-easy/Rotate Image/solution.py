class Solution:

    def rotate(self, matrix: list[list[int]]) -> None:
        """
        Do not return anything, modify matrix in-place instead.
        """
        # just write down a specific row as a column
        # for example, the first row -> the last column
        # a second row -> (the last - 1) column
        # that is, a xth row correspond to an (n-1 = last)-x column

        # assign n which is a matrix's size
        n: int = len(matrix)

        # assign another matrix for rotated values
        rotated_matrix: list[list[int]] = [[-1] * n for _ in range(n)]

        # rotate
        for index in range(n):
            row_index: int = index
            column_index: int = n - 1 - index

            for i in range(n):
                # a row as a column
                rotated_matrix[i][column_index] = matrix[row_index][i]

        # paste the rotated_matrix to the original matrix
        for row_index in range(n):
            for column_index in range(n):
                matrix[row_index][column_index] = rotated_matrix[row_index][column_index]


if __name__ == '__main__':
    Solution().rotate([[1]])
    Solution().rotate([[1, 2], [3, 4]])
    Solution().rotate([[1, 2, 3], [4, 5, 6], [7, 8, 9]])
    Solution().rotate([[5, 1, 9, 11], [2, 4, 8, 10], [13, 3, 6, 7], [15, 14, 12, 16]])
