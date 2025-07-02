import math


def is_valid_index(index, words):
    return 0 <= index < len(words)


def x(a, b):
    r = 0
    for aa, bb in zip(a,b):
        if aa != bb:
            break
        r += 1
    return r


class Solution:

    def longestCommonPrefix(self, words: List[str]) -> List[int]:
        if len(words) == 1:
            return [0]

        prefix_adjacent = []  # (i, j, prefix_length)
        prefix_removed = [-math.inf]  # prefix_removed[removed_index] = prefix_length

        for i in range(len(words) - 1):
            prefix_adjacent.append((i, i+1, x(words[i], words[i+1])))
            if is_valid_index(i-1, words) and is_valid_index(i+1, words):
                prefix_removed.append(x(words[i-1], words[i+1]))

        prefix_removed.append(-math.inf)

        prefix_adjacent.sort(key=lambda x: x[2], reverse=True)

        print(prefix_adjacent)
        print(prefix_removed)

        answer = []
        for i in range(len(words)):
            result = 0
            for ii, jj, prefix_length in prefix_adjacent:
                if ii != i and jj != i:
                    result = prefix_length
                    break
            result = max(result, prefix_removed[i])
            answer.append(result)

        return answer
