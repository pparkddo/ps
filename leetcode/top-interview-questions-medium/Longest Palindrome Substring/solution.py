class Solution:

    def longestPalindrome(self, s: str) -> str:
        dp: list[list[bool]] = [[False] * len(s) for _ in range(len(s))]

        for index in range(len(s)):
            dp[index][index] = True

        palindrome_indices: tuple[int, int] = (0, 0)

        for right_index in range(len(s)):
            for left_index in range(right_index):
                if s[left_index] != s[right_index]:
                    continue
                if left_index + 1 == right_index or dp[left_index+1][right_index-1]:
                    dp[left_index+1][right_index-1] = True
                    if palindrome_indices[1] - palindrome_indices[0] < right_index - left_index:
                        palindrome_indices = (left_index, right_index)

        return s[palindrome_indices[0]:palindrome_indices[1]+1]
