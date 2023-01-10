class Solution:

    def reverseString(self, s: list[str]) -> None:
        """
        Do not return anything, modify s in-place instead.
        """
        # Need two points that come from first and last
        for index in range(len(s)):
            left: int = index
            right: int = len(s) - index - 1

            if left >= right:
                return

            s[left], s[right] = s[right], s[left]
