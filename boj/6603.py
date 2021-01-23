from itertools import combinations


COUNT = 6


def solve(t):
    output = []
    _, *s = t.split()

    for each in combinations(s, COUNT):
        output.append(" ".join(each))
    
    return output


def main():
    output = []
    while True:
        t = input()
        if t == "0":
            break
        output.append(solve(t))
    print("\n\n".join(["\n".join(each) for each in output]))


main()
