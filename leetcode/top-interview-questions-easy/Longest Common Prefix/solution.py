class Solution:

    def longestCommonPrefix(self, strs: list[str]) -> str:
        if not strs:
            return ""

        shortest: str = min(strs, key=len)

        for index, char in enumerate(shortest):
            for str_ in strs:
                if str_[index] != char:
                    return shortest[:index]

        return shortest

