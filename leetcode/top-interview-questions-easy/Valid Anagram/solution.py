def get_letter_counts(word: str) -> dict[str, int]:
    # count letters in the word
    result: dict[str, int] = {}

    for letter in word:
        result[letter] = result.get(letter, 0) + 1

    return result


class Solution:

    def isAnagram(self, s: str, t: str) -> bool:
        s_counts: dict[str, int] = get_letter_counts(s)
        t_counts: dict[str, int] = get_letter_counts(t)

        if len(s_counts) != len(t_counts):
            return False

        for s_letter in s_counts:
            if s_letter not in t_counts:
                return False
            if t_counts[s_letter] != s_counts[s_letter]:
                return False

        return True


if __name__ == '__main__':
    print(Solution().isAnagram("anagram", "nagaram"))
    print(Solution().isAnagram("rat", "car"))
    print(Solution().isAnagram("rac", "car"))
