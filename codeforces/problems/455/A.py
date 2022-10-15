MAX = 100005


def solution(numbers):
    count = [0] * MAX
    dp = [0] * MAX

    for number in numbers:
        count[number] += 1

    dp[0], dp[1] = 0, count[1]

    for number in range(MAX):
        dp[number] = max(dp[number-1], dp[number-2] + number * count[number])

    return dp[MAX-1]


if __name__ == '__main__':
    _ = input()
    print(solution(list(map(int, input().split()))))
