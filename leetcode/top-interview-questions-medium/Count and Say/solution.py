def count_and_say(previous: str, depth: int, n: int) -> str:
    if depth > n:
        return previous
    return count_and_say(convert(previous), depth + 1, n)


def convert(s: str) -> str:
    count: int = 0
    current_number: str | None = None

    result: str = ""

    for char in s:
        if not current_number:
            count = 1
            current_number = char
            continue

        if char == current_number:
            count += 1
        else:
            result = result + str(count) + current_number
            current_number = char
            count = 1

    result = result + str(count) + current_number

    return result


class Solution:

    def countAndSay(self, n: int) -> str:
        base_case: str = "1"

        if n == 1:
            return base_case

        return count_and_say(base_case, 2, n)
