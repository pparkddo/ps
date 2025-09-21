# longest prefix suffix
def build_lps(p):
    lps = [0] * len(p)

    len_ = 0
    i = 1
    n = len(p)

    while i < n:
        if p[i] == p[len_]:
            len_ += 1
            lps[i] = len_
            i += 1
        else:
            if len_ == 0:
                lps[i] = 0
                i += 1
            else:
                len_ = lps[len_-1]

    return lps


def kmp(s, p):
    result = []

    n, m = len(s), len(p)
    lps = build_lps(p)

    i, j = 0, 0
    while i < n:
        if s[i] == p[j]:
            i += 1
            j += 1

            if j == m:
                result.append(i-j)
                j = lps[j - 1]
        else:
            if j == 0:
                i += 1
            else:
                j = lps[j - 1]

    return result


class Solution:
    def strStr(self, haystack: str, needle: str) -> int:
        answer = kmp(haystack, needle)
        return answer[0] if answer else -1
