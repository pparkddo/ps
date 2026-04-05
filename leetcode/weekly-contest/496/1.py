from collections import Counter


class Solution:
    def mirrorFrequency(self, s: str) -> int:
        visited = set()
        counter = Counter(s)

        mirror = {}
        for i in range(10):
            mirror[str(i)] = str(9 - i)
        for i in range(ord("a"), ord("z")+1):
            mirror[chr(i)] = chr(ord("z") + ord("a") - i)
        # print(mirror)

        answer = 0
        for c in s:
            m = mirror[c]
            if c in visited or m in visited:
                continue
            visited.add(c)
            visited.add(m)
            answer += abs(counter[m] - counter[c])

        return answer
