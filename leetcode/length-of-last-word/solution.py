class Solution:

    def lengthOfLastWord(self, s: str) -> int:
        answer = 1

        for index in range(1, len(s)):
            if s[index-1] == " " and s[index] != " ":
                answer = 1
                continue
            if s[index] != " ":
                answer += 1

        return answer
