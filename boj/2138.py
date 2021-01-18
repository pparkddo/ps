import sys; input=sys.stdin.readline


def toggle(value):
    return int(not value)


def change_status(current, index):
    current[index] = toggle(current[index])
    if index > 0:
        current[index-1] = toggle(current[index-1])
    if index < len(current)-1:
        current[index+1] = toggle(current[index+1])
    return current


def simulate(case, expectation, initial):
    count = initial
    for index in range(1, len(case)):
        if case[index-1] != expectation[index-1]:
            change_status(case, index)
            count = count + 1
    if case == expectation:
        return count
    else:
        return -1


def main():
    _ = int(input())
    current = list(map(int, list(input().strip())))
    expectation = list(map(int, list(input().strip())))
    
    if current == expectation:
        print(0)
        return

    cases = [current, change_status(current[:], 0)]
    initials = [0, 1]

    answer = -1
    for case, initial in zip(cases, initials):
        result = simulate(case, expectation, initial)
        if result > 0:
            answer = result
    print(answer)


main()
