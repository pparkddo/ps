class Solution:

    def __init__(self):
        self.answer: bool = False
        self.visited: list[list[bool]] = [[]]
        self.directions: list[tuple[int, int]] = [(-1, 0), (1, 0), (0, -1), (0, 1)]

    def exist(self, board: list[list[str]], word: str) -> bool:
        self.visited = [[False] * len(board[0]) for _ in range(len(board))]

        def traverse(word_index: int, row_index: int, column_index: int):
            if word[word_index] != board[row_index][column_index]:
                return

            if word_index == len(word) - 1 or self.answer:
                self.answer = True
                return

            self.visited[row_index][column_index] = True

            for direction in self.directions:
                next_row_index: int = row_index + direction[0]
                next_column_index: int = column_index + direction[1]

                if not is_valid_index(next_row_index, next_column_index):
                    continue

                if self.visited[next_row_index][next_column_index]:
                    continue

                traverse(word_index+1, next_row_index, next_column_index)

            self.visited[row_index][column_index] = False

        def is_valid_index(row_index: int, column_index: int):
            return 0 <= row_index < len(board) and 0 <= column_index < len(board[0])

        for row in range(len(board)):
            for column in range(len(board[0])):
                traverse(0, row, column)

        return self.answer
