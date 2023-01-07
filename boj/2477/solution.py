from collections import deque


class Solution:

    def __init__(self):
        self.k: int = int(input())

        self.sides: deque[list[int, int]] = deque()
        for _ in range(6):
            self.sides.append(list(map(int, input().split(" "))))

        # get the top side
        self.top_side: list[int, int] = max(self.sides, key=lambda x: x[1])

        # get the right side
        self.right_side: list[int, int] = max(filter(self.is_other_side_from_top, self.sides), key=lambda x: x[1])

        self.arrange()
        area: int = self.top_side[1] * self.right_side[1] - self.sides[2][1] * self.sides[3][1]

        print(area * self.k)

    def is_other_side_from_top(self, side: list[int, int]):
        direction: int = side[0]
        top_direction: int = self.top_side[0]

        if top_direction == 1 or top_direction == 2:
            return direction == 3 or direction == 4
        else:
            return direction == 1 or direction == 2

    def arrange(self):
        while ((self.sides[0] != self.top_side or self.sides[-1] != self.right_side)
               and (self.sides[0] != self.right_side or self.sides[-1] != self.top_side)):
            self.sides.append(self.sides.popleft())


if __name__ == '__main__':
    Solution()
