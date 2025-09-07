class Solution:
    def minOperations(self, s: str) -> int:
        ex = list(filter(lambda x: x != "a", s))

        if len(ex) == 0:
            return 0

        min_ = min(ex)

        return ord("z") - ord(min_) + 1