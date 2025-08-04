import heapq


class Solution:
    def findMaximizedCapital(self, k: int, w: int, profits: List[int], capital: List[int]) -> int:
        pq_by_capital = [(c, p) for p, c in zip(profits, capital)]
        heapq.heapify(pq_by_capital)
        pq_by_profits = []
        # print(pq_by_capital)

        while k:
            while pq_by_capital and pq_by_capital[0][0] <= w:
                p = heapq.heappop(pq_by_capital)[1]
                heapq.heappush(pq_by_profits, -p)

            if pq_by_profits:
                w += -heapq.heappop(pq_by_profits)
            else:
                break

            # print(w)
            k -=1

        return w