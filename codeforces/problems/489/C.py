IMPOSSIBLE = -1


def solution(m, s):
    min_value = get_min_value(m, s)
    max_value = get_max_value(m, s)

    if min_value == IMPOSSIBLE or max_value == IMPOSSIBLE:
        return (IMPOSSIBLE, IMPOSSIBLE)

    return (min_value, max_value)


def get_min_value(m, s):
    if s == 0 and m != 1:
        return IMPOSSIBLE

    numbers = [0] * m
    sum_of_values = 0

    for index in range(m - 1, -1, -1):
        for value in range(9, -1, -1):
            if value == 0 and index == 0:
                continue
            buffer = 0 if index == 0 else 1
            if sum_of_values + value + buffer <= s:
                sum_of_values += value
                numbers[index] = value
                break

    return int("".join(map(str, numbers)))


def get_max_value(m, s):
    if s > 9 * m:
        return IMPOSSIBLE

    numbers = [0] * m
    sum_of_values = 0

    for index in range(m):
        for value in range(9, -1, -1):
            if sum_of_values + value <= s:
                sum_of_values += value
                numbers[index] = value
                break

    return int("".join(map(str, numbers)))


if __name__ == '__main__':
    print(" ".join(map(str, solution(*map(int, input().split())))))
