class Solution:
    def jobScheduling(self, startTime: List[int], endTime: List[int], profit: List[int]) -> int:
        n = len(startTime)
        values = [(startTime[i], endTime[i], profit[i]) for i in range(n)]
        values.sort(key=lambda x: (x[0], -x[2]))
        # print(values)

        dp: dict[int, int] = {}

        def dfs(i, values, n, dp):
            if i in dp:
                return dp[i]

            if i >= n:
                return 0

            not_selected = dfs(i+1, values, n, dp)

            left, right = -1, n
            while left + 1 < right:
                mid = (left + right) // 2
                if values[mid][0] < values[i][1]:
                    left = mid
                else:
                    right = mid

            # print(i, left, right)

            selected = values[i][2] + dfs(left+1, values, n, dp)
            dp[i] = max(not_selected, selected)

            return dp[i]

        answer = dfs(0, values, n, dp)
        # print(dp)
        return answer
