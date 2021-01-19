import sys; input=sys.stdin.readline


for _ in range(int(input())):
    answer = 0
    score = 0
    for each in list(input()):
        if each == "O":
            score = score + 1
            answer = answer + score
        else:
            score = 0
    print(answer)
