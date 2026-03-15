class Solution:
    def minimumIndex(self, capacity: list[int], itemSize: int) -> int:
        capc = [(c, i) for i, c in enumerate(capacity)]
        capc = sorted(filter(lambda x: x[0] >= itemSize, capc), key=lambda x: x[0])
        # print(capc)
        if not capc:
            return -1
        return capc[0][1]
