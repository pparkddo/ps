class SegTree:
    def __init__(self, arr):
        self.n = len(arr)
        self.tree = [0] * (4 * self.n)
        self._build(1, 0, self.n - 1, arr)

    def _build(self, idx, start, end, arr):
        if start == end:
            self.tree[idx] = arr[start]
            return
        mid = (start + end) // 2
        self._build(idx << 1, start, mid, arr)
        self._build(idx << 1 | 1, mid + 1, end, arr)
        self.tree[idx] = max(self.tree[idx << 1], self.tree[idx << 1 | 1])

    def _query(self, idx, start, end, ql, qr):
        if end < ql or start > qr:
            return 0
        if ql <= start and end <= qr:
            return self.tree[idx]
        mid = (start + end) // 2
        return max(
            self._query(idx << 1, start, mid, ql, qr),
            self._query(idx << 1 | 1, mid + 1, end, ql, qr),
        )

    def range_max(self, l, r):
        return self._query(1, 0, self.n - 1, l, r)


class Solution:
    def countLocalMaximums(self, matrix: List[List[int]]) -> int:
        n = len(matrix)
        m = len(matrix[0])

        trees = [SegTree(matrix[i]) for i in range(n)]

        count = 0
        for i in range(n):
            for j in range(m):
                x = matrix[i][j]
                if x == 0:
                    continue

                ru = max(0, i - x)
                rd = min(n - 1, i + x)
                cl = max(0, j - x)
                cr = min(m - 1, j + x)

                f1 = (i - x >= 0) and (j - x >= 0)  # top-left corner exists
                f2 = (i - x >= 0) and (j + x < m)   # top-right corner exists
                f3 = (i + x < n)  and (j - x >= 0)  # bottom-left corner exists
                f4 = (i + x < n)  and (j + x < m)   # bottom-right corner exists

                ans = 0
                for k in range(ru, rd + 1):
                    left, right = cl, cr
                    if (f1 and k == ru) or (f3 and k == rd):
                        left = cl + 1
                    if (f2 and k == ru) or (f4 and k == rd):
                        right = cr - 1
                    ans = max(ans, trees[k].range_max(left, right))

                if ans <= x:
                    count += 1

        return count
