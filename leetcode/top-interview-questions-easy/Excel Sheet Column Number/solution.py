class Solution:

    def titleToNumber(self, columnTitle: str) -> int:
        base: int = 26
        result: int = 0

        for index, char in enumerate(columnTitle):
            weight: int = len(columnTitle) - index - 1
            current: int = ord(char) - ord('A') + 1
            result += current * (base ** weight)

        return result
