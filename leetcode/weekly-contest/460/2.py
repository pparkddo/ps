class Solution:
    def numOfSubsequences(self, s: str) -> int:
        n = len(s)

        prefix = [0 for _ in range(n)]
        suffix = [0 for _ in range(n)]

        prefix[0] = 1 if s[0] == "L" else 0
        for i in range(1, n):
            prefix[i] = prefix[i-1] + (1 if s[i] == "L" else 0)

        suffix[-1] = 1 if s[-1] == "T" else 0
        for i in range(n-2, -1, -1):
            suffix[i] = suffix[i+1] + (1 if s[i] == "T" else 0)

        # print(prefix, suffix)

        result_with_l = 0
        result_with_c = 0
        result_with_t = 0
        best_with_c = 0

        for i in range(n):
            l_count = prefix[i-1] if i > 0 else 0
            t_count = suffix[i+1] if i < n - 1 else 0
            if s[i] == "C":
                result_with_l += (l_count + 1) * t_count
                result_with_c += l_count * t_count
                result_with_t += l_count * (t_count + 1)
            best_with_c = max(best_with_c, prefix[i] * suffix[i])

        result_with_c += best_with_c

        return max(result_with_l, result_with_c, result_with_t)
