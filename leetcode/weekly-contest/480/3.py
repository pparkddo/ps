import heapq


class Solution:
    def minMoves(self, balance: List[int]) -> int:
        if sum(balance) < 0:
            return -1

        target_index = None
        for i, b in enumerate(balance):
            if b < 0:
                target_index = i
                break

        if target_index is None:
            return 0

        n = len(balance)
        positives = []
        for i, b in enumerate(balance):
            if b > 0:
                left_d = (target_index if target_index > i else target_index + n) - i
                right_d = (i if i > target_index else i + n) - target_index
                # print(left_d, right_d)
                d = min(left_d, right_d)
                positives.append((d, i))

        # print(positives)
        heapq.heapify(positives)

        answer = 0
        target = balance[target_index]
        while target == 0:
            d, i = heapq.heappop(positives)
            b = balance[i]
            units = min(-target, b)
            target += units
            answer += d * units

        return answer