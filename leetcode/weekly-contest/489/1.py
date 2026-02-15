from collections import Counter


class Solution:
    def toggleLightBulbs(self, bulbs: list[int]) -> list[int]:
        return sorted([k for k, v in Counter(bulbs).items() if v % 2 == 1])
        