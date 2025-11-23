class Solution:
    def minLengthAfterRemovals(self, s: str) -> int:
        stack = []

        for c in s:
            if stack and stack[-1] == c:
                stack.pop()
            else:
                stack.append(c)

        return len(stack)
