class Solution:

    def isHappy(self, n: int) -> bool:
        set_: set[int] = set()

        def loop(number: int) -> bool:
            if number in set_:
                return False

            set_.add(number)

            next_number: int = 0
            while number:
                next_number += (number % 10) ** 2
                number //= 10

            if next_number == 1:
                return True

            return loop(next_number)

        return loop(n)
