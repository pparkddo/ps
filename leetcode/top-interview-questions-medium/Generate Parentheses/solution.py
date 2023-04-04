class Solution:

    def generateParenthesis(self, n: int) -> list[str]:
        answer: list = []

        def generate(left_count: int, right_count: int, path: str):
            if right_count < left_count:
                return
            if left_count < 0 or right_count < 0:
                return
            if left_count == 0 and right_count == 0:
                answer.append(path)
                return
            generate(left_count - 1, right_count, path + "(")
            generate(left_count, right_count - 1, path + ")")

        generate(n, n, "")

        return answer
