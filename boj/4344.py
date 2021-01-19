import sys; input=sys.stdin.readline


for _ in range(int(input())):
    n, *scores = map(int, input().split())
    average = sum(scores) / len(scores)
    answer = len(list(filter(lambda x: x > average, scores))) / len(scores)
    print(f"{answer:.3%}")
