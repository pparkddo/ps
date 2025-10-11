import sys; input=sys.stdin.readline; sys.setrecursionlimit(10**6)


def main(n, board):
    visited = [[0] * n for _ in range(n)]

    def is_available(r, c):
        return 0 <= r < n and 0 <= c < n

    def dfs(r, c):
        if r == n - 1 and c == n - 1:
            return 1

        if visited[r][c] != 0:
            return visited[r][c]

        if board[r][c] == 0:
            return 0

        # move downward
        if is_available(r+board[r][c], c):
            visited[r][c] += dfs(r+board[r][c], c)

        # move rightward
        if is_available(r, c+board[r][c]):
            visited[r][c] += dfs(r, c+board[r][c])

        return visited[r][c]

    dfs(0, 0)

    return visited[0][0]


if __name__ == '__main__':
    n = int(input())
    board = [list(map(int, input().split())) for _ in range(n)]
    print(main(n, board))
