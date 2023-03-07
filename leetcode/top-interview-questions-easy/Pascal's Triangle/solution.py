class Solution:

    def generate(self, numRows: int) -> list[list[int]]:
        answer: list[list[int]] = [[1]]

        for row_index in range(1, numRows):
            previous_row_index: int = row_index - 1
            previous_row: list[int] = answer[previous_row_index]

            current_row: list[int] = [1]

            for index in range(1, len(previous_row)):
                left_value: int = previous_row[index - 1]
                right_value: int = previous_row[index]
                current_row.append(left_value + right_value)

            current_row.append(1)
            answer.append(current_row)

        return answer
