from collections import Counter


class Solution:
    def longestBalanced(self, s: str) -> int:
        n = len(s)

        counter = Counter(s)

        counts = [(counter["a"], counter["b"], counter["c"])]
        a, b, c = counts[0]
        for char in s:
            if char == "a":
                a -= 1
            if char == "b":
                b -= 1
            if char == "c":
                c -= 1
            counts.append((a, b, c))

        answer = 1
        for i in range(n):
            a, b, c = counts[i]
            if answer >= a + b + c:
                break

            max_ = max(a, b, c)
            if (a if a != 0 else max_) == (b if b != 0 else max_) == (c if c != 0 else max_):
                answer = max(answer, a + b + c)
                break

            for j in range(n-1, -1, -1):
                if s[j] == "a":
                    a -= 1
                if s[j] == "b":
                    b -= 1
                if s[j] == "c":
                    c -= 1

                max_ = max(a, b, c)
                if (a if a != 0 else max_) == (b if b != 0 else max_) == (c if c != 0 else max_):
                    answer = max(answer, a + b + c)
                    break

        return answer