from string import ascii_lowercase


class Solution:
    def lexGreaterPermutation(self, s: str, target: str) -> str:
        freq = [0] * len(ascii_lowercase)
        for c in s:
            freq[ord(c)-ord('a')] += 1

        answer = ""

        def dfs(i, current):
            nonlocal answer

            if answer:
                return

            if len(current) == len(s):
                if current > target:
                    answer = current
                return

            for fi in range(len(freq)):
                if freq[fi] <= 0:
                    continue
                c = chr(fi + ord("a"))
                if current < target and c < target[i]:
                    continue
                if answer:
                    return

                freq[fi] -= 1
                dfs(i + 1, current + c)
                freq[fi] += 1

        dfs(0, "")

        return answer