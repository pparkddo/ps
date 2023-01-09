BLANK = "."


def partition(values: list[int], size: int) -> list[list[int]]:
    return [values[index:index + size] for index in range(0, len(values), size)]


class Solution:

    def isValidSudoku(self, board: list[list[str]]) -> bool:
        # Rule 1 : Each row must contain the digits 1-9 without repetition.
        # How to check : Check in row direction
        # 9 * 9 operations
        for row in board:
            set_: set[str] = set()
            for each in row:
                if each == BLANK:
                    continue
                if each in set_:
                    return False
                set_.add(each)

        # Rule 2 : Each column must contain the digits 1-9 without repetition.
        # How to check : Check in column direction
        # 9 * 9 operations
        for column_index in range(9):
            set_: set[str] = set()
            for row_index in range(9):
                each: str = board[row_index][column_index]
                if each == BLANK:
                    continue
                if each in set_:
                    return False
                set_.add(each)

        # Rule 3: Each of the nine 3 * 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
        # How to check : Check each sub-boxes
        # 9 * 9 operations
        sub_box_indices: list[list[int]] = partition(list(range(9)), 3)
        for row_indices in sub_box_indices:
            for column_indices in sub_box_indices:
                set_: set[str] = set()
                for row_index in row_indices:
                    for column_index in column_indices:
                        each: str = board[column_index][row_index]
                        if each == BLANK:
                            continue
                        if each in set_:
                            return False
                        set_.add(each)

        return True


if __name__ == '__main__':
    print(Solution().isValidSudoku(
        [[".", ".", "4", ".", ".", ".", "6", "3", "."],
         [".", ".", ".", ".", ".", ".", ".", ".", "."],
         ["5", ".", ".", ".", ".", ".", ".", "9", "."],
         [".", ".", ".", "5", "6", ".", ".", ".", "."],
         ["4", ".", "3", ".", ".", ".", ".", ".", "1"],
         [".", ".", ".", "7", ".", ".", ".", ".", "."],
         [".", ".", ".", "5", ".", ".", ".", ".", "."],
         [".", ".", ".", ".", ".", ".", ".", ".", "."],
         [".", ".", ".", ".", ".", ".", ".", ".", "."]]))
