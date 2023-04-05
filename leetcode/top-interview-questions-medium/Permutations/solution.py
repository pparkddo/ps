class Solution:

    def permute(self, nums: list[int]) -> list[list[int]]:
        answer: list[list[int]] = []

        temp: list[int] = [-1] * len(nums)
        visited: list[bool] = [False] * len(nums)

        def dfs(depth: int) -> None:
            if depth == len(nums):
                answer.append(temp[::])
                return

            for index, num in enumerate(nums):
                if visited[index]:
                    continue
                temp[depth] = num
                visited[index] = True
                dfs(depth+1)
                visited[index] = False

        dfs(0)

        return answer
