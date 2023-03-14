from collections import defaultdict
from string import ascii_lowercase


def lowercase_alphabet_hash(s: str) -> tuple[int]:
    result: list[int] = [0] * len(ascii_lowercase)

    for c in s:
        result[ord(c) - ord("a")] += 1

    return tuple(result)


class Solution:

    def groupAnagrams(self, strs: list[str]) -> list[list[str]]:
        d: dict[tuple[int], list[str]] = defaultdict(list)

        for index, s in enumerate(strs):
            d[lowercase_alphabet_hash(s)].append(s)

        return list(d.values())


if __name__ == '__main__':
    print(Solution().groupAnagrams(["eat","tea","tan","ate","nat","bat"]))

