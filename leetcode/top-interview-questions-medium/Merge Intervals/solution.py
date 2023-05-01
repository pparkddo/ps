def can_merge(a: list[int], b: list[int]):
    return a[0] <= b[0] <= a[1]


class Solution:

    def merge(self, intervals: list[list[int]]) -> list[list[int]]:
        intervals.sort(key=lambda x: x[0])

        answer: list[list[int]] = [intervals[0]]

        for index in range(1, len(intervals)):
            a: list[int] = answer[-1]
            b: list[int] = intervals[index]

            if can_merge(a, b):
                answer[-1][1] = max(answer[-1][1], intervals[index][1])
            else:
                answer.append(b)

        return answer
