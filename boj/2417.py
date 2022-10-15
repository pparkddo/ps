def solution():
    n: int = int(input())

    return binary_search(n)


def binary_search(n) -> int:
    answer = -1

    left, right = 0, n

    while left <= right:
        mid: int = (left + right) // 2

        if mid * mid >= n:
            answer = mid
            if (mid-1) * (mid-1) < n:
                return answer
            else:
                right = mid - 1
        else:
            left = mid + 1

    return answer


print(solution())
