import sys; input=sys.stdin.readline
from collections import Counter


def count_preference(preferences):
    return Counter([p[0] for p in preferences])


def sort_count(count):
    return sorted(count.items(), key=lambda x: x[1], reverse=True)


def filter_candidates(preferences, a, b):
    filtered = []
    for preference in preferences:
        filtered.append(list(filter(lambda x: x == a or x == b, preference)))
    return filtered


t = int(input())
for _ in range(t):
    c, v = map(int, input().split())
    half = v // 2
    preferences = [list(map(int, input().split())) for _ in range(v)]

    sorted_count = sort_count(count_preference(preferences))
    if sorted_count[0][1] > half:
        print(sorted_count[0][0], 1)
        continue
    
    filtered = filter_candidates(preferences, sorted_count[0][0], sorted_count[1][0])
    sorted_count = sort_count(count_preference(filtered))
    if sorted_count[0][1] > half:
        print(sorted_count[0][0], 2)
