class Solution:

    def processStr(self, s: str) -> str:
        answer = ""

        for c in s:
            # print(answer)
            if c.isalpha():
                answer += c
            elif c == "*" and len(c) >= 1:
                answer = answer[:-1]
            elif c == "#":
                answer += answer
            elif c == "%":
                answer = answer[::-1]

        return answer
