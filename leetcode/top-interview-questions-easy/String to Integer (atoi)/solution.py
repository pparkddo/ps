from string import digits

digits_set: set[int] = set(digits)
INT_MAX = (1 << 31) - 1
INT_MIN = -(1 << 31)


class Solution:

    def myAtoi(self, s: str) -> int:
        if not s or s.isspace():
            return 0

        index: int = 0
        sign: int = 1
        result: int = 0

        # ignore leading whitespaces
        for letter in s:
            if letter != " ":
                break
            index += 1

        # if the first letter is sign, then assign the sign
        if s[index] == "+" or s[index] == "-":
            sign = 1 if s[index] == "+" else -1
            index += 1

        # read letters and add value to result
        while index < len(s) and s[index] in digits_set:
            value = int(s[index])
            # check result exceed limits
            if result > INT_MAX // 10 or (result == INT_MAX // 10 and value > INT_MAX % 10):
                return INT_MAX if sign == 1 else INT_MIN
            result = result * 10 + value
            index += 1

        return sign * result


if __name__ == '__main__':
    print(Solution().myAtoi("42"))
    print(Solution().myAtoi("    -42"))
    print(Solution().myAtoi("  4193 d"))
    print(Solution().myAtoi("  -4193 d"))
    print(Solution().myAtoi("d"))
    print(Solution().myAtoi("   -d"))
    print(Solution().myAtoi("   - d"))
    print(Solution().myAtoi("  41d93 d"))
    print(Solution().myAtoi(" "))
    print(Solution().myAtoi("-91283472332"))
    print(Solution().myAtoi("91283472332"))
    print(Solution().myAtoi("-2147483648"))
    print(Solution().myAtoi("+-12"))
    print(Solution().myAtoi("00000-42a1234"))
    print(Solution().myAtoi(""))
    print(Solution().myAtoi("21474836460"))
    print(Solution().myAtoi("2147483648"))
