def is_prime_number(number):
    if number <= 1:
        return False
    for i in range(2, number):
        if (number % i) == 0:
            return False
    return True


def solution(numbers):
    from itertools import permutations, combinations
    container = {}  # {prime_number: True,}
    for count in range(1, len(numbers)+1):
        possibles = [int("".join(each)) for each in list(permutations(numbers, count))]
        for possible in possibles:
            if is_prime_number(possible) and possible not in container:
                container.update({possible: True})
    return len(container)


if __name__ == "__main__":
    questions = [
        "17",
        "011",
    ]
    answers = [
        3,
        2,
    ]

    for q, a in zip(questions, answers):
        answer = solution(q)
        if a == answer:
            print("[o] good")
        else:
            print("[x] bad", answer)
