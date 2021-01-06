from collections import deque


def solution(prices):
    next_values = deque()
    answer = []
    for price, next_price in zip(prices[-2::-1], prices[::-1]):
        second = 0
        next_values.appendleft(next_price)
        for each in next_values:
            second = second + 1
            if price <= each:
                continue
            else:
                break
        answer.append(second)
    return [*answer[::-1], 0]


if __name__ == "__main__":
    questions = [
        [1, 2, 3, 2, 3],
        [1, 2, 3, 2, 3, 3, 1],
    ]
    answers = [
        [4, 3, 1, 1, 0],
        [6, 5, 1, 3, 2, 1, 0],
    ]

    for q, a in zip(questions, answers):
        answer = solution(q)
        if a == answer:
            print("[o] good")
        else:
            print("[x] bad", answer)
