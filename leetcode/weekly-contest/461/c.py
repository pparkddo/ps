from sortedcontainers import SortedList


class Solution:
    def minTime(self, s: str, order: List[int], k: int) -> int:
        n = len(order)

        l = SortedList([-1, n])
        for time, o in enumerate(order):
            left, right = 0, len(l)
            while left + 1 < right:
                mid = (left + right) // 2
                if l[mid] < o:
                    left = mid
                else:
                    right = mid
            ll = l[left]

            left, right = 0, len(l)
            while left + 1 < right:
                mid = (left + right) // 2
                if l[mid] <= o:
                    left = mid
                else:
                    right = mid
            rr = l[right]

            # print(ll, rr)

            k -= (o - ll) * (rr - o)
            if k <= 0:
                return time

            l.add(o)

        return -1
