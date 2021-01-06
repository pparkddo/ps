START_QUERY = "start"
END_QUERY = "end"
WILDCARD = "?"


def solution(words, queries):
    answer = []
    for query in queries:
        matched_count = 0
        query_length = len(query)
        query_type = get_query_type(query)
        for word in words:
            word_length = len(word)
            if not is_same_length(word_length, query_length):
                continue
            if is_matched(word, query, query_type):
                matched_count = matched_count + 1
        answer.append(matched_count)
    return answer


def get_query_type(query):
    if not is_wildcard_character(query[0]):
        return START_QUERY
    else:
        return END_QUERY


def is_same_length(word_length, query_length):
    return word_length == query_length


def is_matched(word, query, query_type):
    if query_type == START_QUERY:
        for w, q in zip(word, query):
            if is_wildcard_character(q):
                break
            if not w == q:
                return False
        return True
    else:
        for w, q in zip(word[::-1], query[::-1]):
            if is_wildcard_character(q):
                break
            if not w == q:
                return False
        return True


def is_wildcard_character(c):
    return c == WILDCARD


if __name__ == "__main__":
    words = ["frodo", "front", "frost", "frozen", "frame", "kakao"]
    queries = ["fro??", "????o", "fr???", "fro???", "pro?"]

    print(solution(words, queries))
