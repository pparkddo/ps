class Solution:

    def strStr(self, haystack: str, needle: str) -> int:
        # return the index of the first occurrence of 'needle' in 'haystack'
        # to implement faster version, it needs to be implemented by KMP
        # here, I would like to implement easier version instead.
        # that is O(len(haystack) * len(needle))

        # we don't have to iterate at an index higher than the length of the 'haystack' minus the length of the 'needle'
        # because it can't be matched on values greater than that index.
        for index in range(len(haystack)-len(needle)+1):
            if haystack[index:index+len(needle)] == needle:
                return index

        return -1  # not found


if __name__ == '__main__':
    print(Solution().strStr("sadbutsad", "sad"))
    print(Solution().strStr("leetcode", "leeto"))
    print(Solution().strStr("leetcode", "co"))
    print(Solution().strStr("a", "a"))
    print(Solution().strStr("abc", "c"))
    print(Solution().strStr("abc", "b"))
