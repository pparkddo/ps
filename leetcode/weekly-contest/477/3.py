class Solution:

    def sumAndMultiply(self, s: str, queries: List[List[int]]) -> List[int]:
        n = len(s)
        mod = 10 ** 9 + 7
        non_zero_count = [0] * (n + 1)
        pre_num = [0] * (n + 1)
        pre_sum = [0] * (n + 1)
        pow10 = [1] * (n + 1)

        for i in range(1, n + 1):
            pow10[i] = (pow10[i - 1] * 10) % mod

        for i in range(n):
            d = int(s[i])

            non_zero_count[i + 1] = non_zero_count[i]
            pre_num[i + 1] = pre_num[i]
            pre_sum[i + 1] = pre_sum[i]

            if d != 0:
                non_zero_count[i + 1] += 1
                pre_num[i + 1] = (pre_num[i] * 10 + d) % mod
                pre_sum[i + 1] += d

        # print(non_zero_count)
        # print(pre_num)
        # print(pre_sum)
        # print(pow10)

        answer = []
        for l, r in queries:
            count = non_zero_count[r + 1] - non_zero_count[l]
            sum_ = pre_sum[r + 1] - pre_sum[l]

            if count == 0:
                answer.append(0)
            else:
                x = (pre_num[r + 1] - pre_num[l] * pow10[count]) % mod
                answer.append((x * sum_) % mod)

        return answer
