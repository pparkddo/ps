class Solution:

    def letterCombinations(self, digits: str) -> list[str]:
        if not digits:
            return []

        letter_by_digit: dict[str, list[str]] = {
            "2": ["a", "b", "c"],
            "3": ["d", "e", "f"],
            "4": ["g", "h", "i"],
            "5": ["j", "k", "l"],
            "6": ["m", "n", "o"],
            "7": ["p", "q", "r", "s"],
            "8": ["t", "u", "v"],
            "9": ["w", "x", "y", "z"],
        }

        answer: list[str] = []

        def traverse(word: str, depth: int):
            if depth == len(digits):
                answer.append(word)
                return

            for each in letter_by_digit[digits[depth]]:
                traverse(word + each, depth+1)

        traverse("", 0)

        return answer
