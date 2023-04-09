import heapq
from collections import Counter


class Solution:

    def topKFrequent(self, nums: list[int], k: int) -> list[int]:
        counter: dict[int, int] = Counter(nums)

        heap: list[tuple[int, int]] = []

        for key, count in counter.items():
            heapq.heappush(heap, (count, key))

        return [key for _, key in heapq.nlargest(k, heap)]
