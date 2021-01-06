def solution(numbers):
    max_length = len(str(max(numbers, key=lambda x: len(str(x)))))

    filled = []  # [(modified: origin), ...]

    for number in numbers:
        # first_char = str(number)[:1]
        # modified = int(str(number).ljust(max_length, first_char))
        modified = get_modified(number, max_length)
        filled.append((modified, number))

    sorted_filled = sorted(filled, key=lambda x: x[0], reverse=True)
    return str(int("".join([str(each[1]) for each in sorted_filled])))


def get_modified(number, max_length=5):
    modified = str(number)

    while len(modified) < max_length:
        modified = modified * 2

    return modified[:max_length]


# def solution(numbers):
#     from itertools import permutations
#     ps = ["".join([str(e) for e in each]) for each in permutations(numbers)]
#     ps.sort(reverse=True)
#     return ps[0]


if __name__ == "__main__":
    questions = [
        [6, 10, 2],
        [3, 30, 34, 5, 9],
        [30, 300, 333],
        [342, 312, 3],
        [0, 5, 10, 15, 20],
        [1000, 0, 5, 99, 100],
        [0, 0, 0, 0, 0],
        [0, 0, 0, 1000],
        [40, 403],
        [2, 20, 200],
        [23, 233],
        [233, 23],
    ]
    answers = [
        "6210",
        "9534330",
        "33330300",
        "3423312",
        "52015100",
        "99510010000",
        "0",
        "1000000",
        "40403",
        "220200",
        "23323",
        "23323",
    ]

    for q, a in zip(questions, answers):
        answer = solution(q)
        if a == answer:
            print("[o] good")
        else:
            print("[x] bad", answer)
