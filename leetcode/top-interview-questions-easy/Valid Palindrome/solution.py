from string import ascii_letters, digits

printable_letters = set(ascii_letters + digits)


def is_alphanumeric(s: str) -> bool:
    # we need a function that checks a sentence whether alphanumeric or not.
    return s in printable_letters


class Solution:

    def isPalindrome(self, s: str) -> bool:
        # before we start, we can do some advance works such as removing white spaces or non-alphanumeric characters
        # and converting uppercase letters to lowercase letters.
        refined_s: str = "".join(map(lambda x: x.lower(), filter(lambda x: is_alphanumeric(x), s)))

        # we can check a palindrome using a recursive function.
        # the function checks if the letter of right_index and the letter of left_index is same.
        # and pass next indexes to an inner (=recursive) function.
        # next indexes are consist of getting closer inward indexes.
        # if the indexes cross, in other words, the left index is greater than the right index,
        # or if the indexes are out of index,
        # we should return the function.
        return self._rec(refined_s, 0, len(refined_s) - 1)

    def _rec(self, s: str, left_index: int, right_index: int) -> bool:
        if left_index > right_index:
            return True

        if s[left_index] != s[right_index]:
            return False

        return self._rec(s, left_index + 1, right_index - 1)


if __name__ == '__main__':
    print(Solution().isPalindrome("A man, a plan, a canal: Panama"))
    print(Solution().isPalindrome("Race a car"))
    print(Solution().isPalindrome(" "))
    print(Solution().isPalindrome("r"))
    print(Solution().isPalindrome("abc"))
    print(Solution().isPalindrome("abcba"))
    print(Solution().isPalindrome("abccba"))
