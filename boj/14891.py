from collections import deque


WHEEL_COUNT = 4
N_POLAR = "0"
S_POLAR = "1"
CLOCKWISE = 1
COUNTER_CLOCKWISE = -1
RIGHT_FACING_INDEX = 2
LEFT_FACING_INDEX = 6


def get_rotate_wise(n, w, index):
    if n % 2 == (index+1) % 2:
        return w
    else:
        return -w


def get_score(wheels):
    score = 0
    for index in range(WHEEL_COUNT):
        if wheels[index][0] == S_POLAR:
            score = score + 2 ** index
    return score


wheels = []
for _ in range(WHEEL_COUNT):
    wheels.append(deque(input()))


for _ in range(int(input())):
    n, w = map(int, input().split())
    
    rotates = [0] * 4

    # to left
    for index in range(n-1, 0, -1):
        is_same_polar_facing = wheels[index][LEFT_FACING_INDEX] == wheels[index-1][RIGHT_FACING_INDEX]
        if is_same_polar_facing:
            break
        if rotates[index] == 0:
            rotates[index] = get_rotate_wise(n, w, index)
        if rotates[index-1] == 0:
            rotates[index-1] = get_rotate_wise(n, w, index-1)

    # to right
    for index in range(n-1, WHEEL_COUNT-1):
        is_same_polar_facing = wheels[index][RIGHT_FACING_INDEX] == wheels[index+1][LEFT_FACING_INDEX]
        if is_same_polar_facing:
            break
        if rotates[index] == 0:
            rotates[index] = get_rotate_wise(n, w, index)
        if rotates[index+1] == 0:
            rotates[index+1] = get_rotate_wise(n, w, index+1)

    rotates[n-1] = w

    for index, rotate in enumerate(rotates):
        wheels[index].rotate(rotate)
    
print(get_score(wheels))
