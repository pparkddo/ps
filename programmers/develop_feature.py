def convert_to_days(progresses, speeds):
    import math
    from collections import deque
    return deque([math.ceil((100 - progress) / speed) for progress, speed in zip(progresses, speeds)])


def solution(progresses, speeds):
    from collections import defaultdict
    days = convert_to_days(progresses, speeds)
    deploys = defaultdict(int)  # {deploy_day: count,}
    deploy_day = 0
    while days:
        day = days.popleft()
        if day >= deploy_day:
            deploy_day = day
        deploys[deploy_day] = deploys[deploy_day] + 1
    return list(deploys.values())


if __name__ == "__main__":
    questions = [
        ([93, 30, 55], [1, 30, 5]),
        ([95, 90, 99, 99, 80, 99], [1, 1, 1, 1, 1, 1]),
    ]
    answers = [
        [2, 1],
        [1, 3, 2],
    ]

    for q, a in zip(questions, answers):
        answer = solution(*q)
        if a == answer:
            print("[o] good")
        else:
            print("[x] bad", answer)
