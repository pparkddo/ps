import sys; input = sys.stdin.readline
import math


# def solution(n, m, matrix):
#     max_value = -math.inf
#     for i in range(n):
#         for j in range(m):
#             max_value = max(max_value, matrix[i][j])
#
#     # print(max_value)
#
#     rc, cc = {}, {}
#     max_value_points = []
#     for i in range(n):
#         for j in range(m):
#             if matrix[i][j] == max_value:
#                 rc[i] = rc.get(i, 0) + 1
#                 cc[j] = cc.get(j, 0) + 1
#                 max_value_points.append((i, j))
#
#     # print(rc, cc, max_value_points)
#
#     if len(rc) == 1 or len(cc) == 1:
#         return max_value - 1
#
#     maximum_row_index, maximum_row_value = sorted(rc.items(), key=lambda x: x[1])[-1]
#     if maximum_row_value != 1:
#         s = set()
#         for r, c in max_value_points:
#             if r == maximum_row_index:
#                 continue
#             if not s:
#                 s.add(c)
#                 continue
#             if c not in s:
#                 return max_value
#
#         return max_value - 1
#
#     maximum_column_index, maximum_column_value = sorted(cc.items(), key=lambda x: x[1])[-1]
#     s = set()
#     for r, c in max_value_points:
#         if c == maximum_column_index:
#             continue
#         if not s:
#             s.add(r)
#             continue
#         if r not in s:
#             return max_value
#
#     return max_value - 1


def solution(n, m, matrix):
    max_value = -math.inf
    for i in range(n):
        for j in range(m):
            max_value = max(max_value, matrix[i][j])

    max_count = 0
    for i in range(n):
        for j in range(m):
            if matrix[i][j] == max_value:
                max_count += 1

    # print(max_value)

    rc, cc = {}, {}
    for i in range(n):
        for j in range(m):
            if matrix[i][j] == max_value:
                rc[i] = rc.get(i, 0) + 1
                cc[j] = cc.get(j, 0) + 1

    # print(rc, cc)

    if len(rc) == 1 or len(cc) == 1:
        return max_value - 1

    for i in range(n):
        for j in range(m):
            adjustment = -1 if matrix[i][j] == max_value else 0
            if rc.get(i, 0) + cc.get(j, 0) + adjustment == max_count:
                return max_value - 1

    return max_value


if __name__ == '__main__':
    for _ in range(int(input())):
        n, m = map(int, input().split())
        matrix = []
        for _ in range(n):
            matrix.append(list(map(int, input().split())))
        print(solution(n, m, matrix))
