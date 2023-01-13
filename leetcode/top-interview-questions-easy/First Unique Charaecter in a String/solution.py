from collections import defaultdict


class Solution:

    def firstUniqChar(self, s: str) -> int:
        # we need each letter's counts and indices
        # so, we put each letter as a key and its index as a value which is a list in a dictionary
        indices_by_letter: dict[str, list[int]] = defaultdict(list)
        for index, letter in enumerate(s):
            indices_by_letter[letter].append(index)

        # get items that have one element in the list
        # if there is no itesm then return -1
        items: list[str, list[int]] = list(filter(lambda item: len(item[1]) == 1, indices_by_letter.items()))
        if not items:
            return -1

        # compare the first index (element) of the list
        # and take the smallest one.
        return min(items, key=lambda item: item[1][0])[1][0]


if __name__ == '__main__':
    print(Solution().firstUniqChar("leetcode"))
    print(Solution().firstUniqChar("loveleetcode"))
    print(Solution().firstUniqChar("aabb"))
