import math


class Solution:
    def maxBalancedShipments(self, weight: List[int]) -> int:
        answer = 0

        i = 1
        n = len(weight)
        max_ = weight[0]
        while i < n:
            # print(i, weight[i], max_)
            if weight[i] < max_:
                answer += 1
                i += 1
                max_ = -math.inf
            if i == n:
                break
            max_ = max(max_, weight[i])
            i += 1

        return answer