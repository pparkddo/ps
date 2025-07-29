class Solution:
    def numOfSubsequences(self, s: str) -> int:
        n = len(s)

        prefix = [0 for _ in range(n+1)]
        suffix = [0 for _ in range(n+1)]

        for i in range(n):
            if s[i] == "L":
                prefix[i+1] = 1
            prefix[i+1] += prefix[i]

        for i in range(n-1, -1, -1):
            if s[i] == "T":
                suffix[i] = 1
            suffix[i] += suffix[i+1]

        # print(prefix, suffix)

        result_with_l = 0
        result_with_c = 0
        result_with_t = 0
        best_with_c = 0

        for i in range(n):
            if s[i] == "C":
                result_with_l += (prefix[i] + 1) * suffix[i+1]
                result_with_c += prefix[i] * suffix[i+1]
                result_with_t += prefix[i] * (suffix[i+1] + 1)
            else:
                best_with_c = max(best_with_c, prefix[i] * suffix[i])

        result_with_c += best_with_c

        return max(result_with_l, result_with_c, result_with_t)
