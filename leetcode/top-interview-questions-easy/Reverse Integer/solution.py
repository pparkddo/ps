def convert_to_str_array(value: int) -> list[str]:
    return list(str(value))


def reverse_array(values: list[str]) -> list[str]:
    if values[0] == "-":
        return ["-", *values[1:][::-1]]
    else:
        return values[::-1]


def is_greater_than(a: list[str], b: list[str]) -> bool:
    if len(a) > len(b):
        return True

    if len(a) == len(b):
        for i, j in zip(a, b):
            if i > j:
                return True
            elif i == j:
                continue
            else:
                return False

    return False


def is_less_than(a: list[str], b: list[str]) -> bool:
    if len(a) > len(b):
        return True

    if len(a) == len(b):
        for i, j in zip(a, b):
            if i > j:
                return True
            elif i == j:
                continue
            else:
                return False

    return False


class Solution:

    def reverse(self, x: int) -> int:
        # make x to a char(str) array
        x_array: list[str] = convert_to_str_array(x)

        # reverse the x array
        reversed_x_array: list[str] = reverse_array(x_array)

        # generate max and min
        max_array: list[str] = convert_to_str_array(2 ** 31 - 1)
        min_array: list[str] = convert_to_str_array(-2 ** 31)

        # compare to max and min
        # if the value is not in the range
        # then return 0
        if x > 0 and is_greater_than(reversed_x_array, max_array):
            return 0

        if x < 0 and is_less_than(reversed_x_array, min_array):
            return 0

        # if the value is in the range
        # return the reversed value
        return int("".join(reversed_x_array))


if __name__ == '__main__':
    print(convert_to_str_array(123)[::-1])
    print(convert_to_str_array(-2**31))
    print(reverse_array(list(str("-123"))))

    print(is_greater_than(["3", "1"], ["2", "3"]))
    print(is_greater_than(["1", "3", "1"], ["2", "3"]))
    print(is_greater_than(["1"], ["2", "3"]))

    print(Solution().reverse(123))
    print(Solution().reverse(-123))
    print(Solution().reverse(120))
    print(Solution().reverse(1120))
    print(Solution().reverse(int("".join(reverse_array(convert_to_str_array(2**31))))))
    print(Solution().reverse(int("".join(reverse_array(convert_to_str_array(-2**31))))))
    print(Solution().reverse(int("".join(reverse_array(convert_to_str_array(-2**31-1))))))
