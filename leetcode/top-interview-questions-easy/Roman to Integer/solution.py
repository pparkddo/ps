class Solution:

    def romanToInt(self, s: str) -> int:
        answer: int = 0

        map_: dict[str, int] = {"I": 1, "V": 5, "X": 10, "L": 50, "C": 100, "D": 500, "M": 1000}

        previous: None | int = None
        for c in s:
            current: int = map_[c]
            if previous and previous >= current:
                answer += previous
            if previous and previous < current:
                answer -= previous
            previous = current

        return answer + previous
