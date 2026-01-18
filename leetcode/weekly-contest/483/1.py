class Solution:
    def largestEven(self, s: str) -> str:
        answer = ""

        even = False
        for c in s[::-1]:
            if not even and c == "1":
                continue
            even = True
            answer = c + answer

        return answer
