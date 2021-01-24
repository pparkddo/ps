import math

def main():
    a, b, v = map(int, input().split())

    # if a <= b and a >= v:
    #     print(1)
    #     return

    # if a <= b:
    #     print(-1)
    #     return

    print(int(math.ceil((v-b)/(a-b))))


main()
