class Solution:
    def minCost(self, n: int) -> int:
        p2 = [2 ** x for x in range(10)]
        # print(p2)

        answer = 0

        def dfs(x):
            nonlocal answer
            # print(x, answer)
            if x == 2:
                answer += 1
                return
            if x == 1:
                return

            if x in p2:
                answer += ((x//2) ** 2)
                dfs(x // 2)
                dfs(x // 2)
                return
            else:
                highest = -1
                for p in p2:
                    if x < p:
                        break
                    highest = p
                answer += ((x-highest) * highest)
                dfs(highest)
                dfs(x - highest)
                return

        dfs(n)
        return answer
