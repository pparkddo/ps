# The isBadVersion API is already defined for you.
def isBadVersion(version: int) -> bool:
    pass


class Solution:

    def firstBadVersion(self, n: int) -> int:
        answer: int = -1

        left: int = 1
        right: int = n

        while left <= right:
            mid: int = (left + right) // 2

            if isBadVersion(mid):
                right = mid - 1
                answer = mid
            else:
                left = mid + 1

        return answer
