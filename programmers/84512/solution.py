WORDS = "AEIOU"
ANSWER = 0
IS_FINISHED = False


def solution(words):
    global ANSWER, IS_FINISHED

    current_words = [0] * len(WORDS)
    ANSWER, IS_FINISHED = 0, False
    dfs(current_words, 0, words)
    return ANSWER


def dfs(current_words, depth, words):
    global ANSWER, IS_FINISHED

    if IS_FINISHED:
        return

    if is_matched_with_target(current_words, words):
        IS_FINISHED = True
        return

    ANSWER += 1

    if depth == len(WORDS):
        return

    for i in range(len(WORDS)):
        current_words[depth] = WORDS[i]
        dfs(current_words, depth + 1, words)
        current_words[depth] = 0


def is_matched_with_target(current_words, words):
    for current, word in zip(current_words, words):
        if current != word:
            return False
    return True


if __name__ == '__main__':
    print(solution("AAAAE"))
    print(solution("AAAE"))
    print(solution("I"))
    print(solution("EIO"))
