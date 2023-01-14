class Solution:

    def fizzBuzz(self, n: int) -> list[str]:
        result: list[str] = []

        for index in range(1, n + 1):
            # check the bigger common multiple first
            # in this case, 15
            if index % 15 == 0:
                result.append("FizzBuzz")
            elif index % 3 == 0:
                result.append("Fizz")
            elif index % 5 == 0:
                result.append("Buzz")
            else:
                result.append(str(index))

        return result


if __name__ == '__main__':
    print(Solution().fizzBuzz(3))
