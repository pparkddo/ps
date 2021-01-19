import sys; input=sys.stdin.readline

n = int(input())
distances = list(map(int, input().split()))
prices = list(map(int, input().split()))

oil, cost = 0, 0

for index in range(len(distances)):
    distance, price = distances[index], prices[index]

    if oil != 0:
        oil = oil - distance
        continue

    oil = oil + distance

    for next_index in range(index+1, len(distances)):
        if price > prices[next_index]:
            break
        oil = oil + distances[next_index]

    cost = cost + oil * price

    oil = oil - distance

print(cost)
