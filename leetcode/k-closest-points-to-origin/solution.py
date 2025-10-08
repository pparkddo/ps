# quick select
def partition(l, r, arr):
    pivot = arr[r]
    i = l
    for j in range(l, r):
        # if arr[j] <= pivot:
        if compare_by_distance(arr[j], pivot):
            arr[i], arr[j] = arr[j], arr[i]
            i += 1
    arr[i], arr[r] = arr[r], arr[i]
    return i


def compare_by_distance(a, b):
    return a[0] ** 2 + a[1] ** 2 <= b[0] ** 2 + b[1] ** 2


class Solution:
    def kClosest(self, points: List[List[int]], k: int) -> List[List[int]]:
        n = len(points)
        l, r = 0, n - 1

        while l < r:
            mid = partition(l, r, points)
            if mid == k:
                break
            elif mid < k:
                l = mid + 1
            else:
                r = mid - 1

        return points[:k]
