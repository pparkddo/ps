import math


class Solution:
    def internalAngles(self, sides: list[int]) -> list[float]:
        sides.sort()
        # print(sides)
        
        is_triangle = sides[0] + sides[1] > sides[2]
        # print(is_triangle)
        if not is_triangle:
            return []

        def func(a, b, c):
            # print(a, b, c, (a**2 + b**2 - c**2) / (2*a*b))
            return math.degrees(math.acos((a**2 + b**2 - c**2) / (2*a*b)))

        return sorted([
            func(sides[0], sides[1], sides[2]),
            func(sides[1], sides[2], sides[0]),
            func(sides[0], sides[2], sides[1])
        ])
