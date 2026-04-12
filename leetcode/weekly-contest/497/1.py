class Solution:
    def findDegrees(self, matrix: list[list[int]]) -> list[int]:
        n = len(matrix)
        answer = [0] * n

        for i in range(n):
            for j in range(n):
                answer[i] += matrix[i][j]

        return answer
