class Solution:

    def isValid(self, s: str) -> bool:
        stack: list[str] = []

        for char in s:
            if char == "(" or char == "[" or char == "{":
                stack.append(char)
                continue

            if char == ")" and (not stack or stack.pop() != "("):
                return False

            if char == "]" and (not stack or stack.pop() != "["):
                return False

            if char == "}" and (not stack or stack.pop() != "{"):
                return False

        return not stack
