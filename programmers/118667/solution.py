from collections import deque

INVALID = -1


def solution(queue1, queue2):
    sum1, sum2 = sum(queue1), sum(queue2)

    if (sum1 + sum2) % 2 != 0:
        return INVALID

    answer = 0

    if sum1 == sum2:
        return answer

    queue1, queue2 = deque(queue1), deque(queue2)

    max_ = len(queue1) + len(queue2) + 1

    while sum1 != sum2:
        if len(queue1) == 0 or len(queue2) == 0:
            return INVALID

        if answer > max_:
            return INVALID

        if sum1 > sum2:
            val = queue1.popleft()
            queue2.append(val)
            sum1 -= val
            sum2 += val
        else:
            val = queue2.popleft()
            queue1.append(val)
            sum2 -= val
            sum1 += val

        answer += 1

    return answer


if __name__ == '__main__':
    print(solution([3, 2, 7, 2], [4, 5, 6, 1]))
    print(solution([1, 2, 1, 2], [1, 10, 1, 2]))
    print(solution([1, 1], [1, 5]))
