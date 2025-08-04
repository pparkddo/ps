from collections import Counter


# [initialization]
# Assign the count of each word to count
# Assign empty dict to visited

# [how to slide]
# if the word at right index matches words
# move right by word len
# else
# move left by word len while left is not equal to right
# and move right by word len

# [what to do when an index move]
# when right moves:
# if word in words:
# visited[word] += 1
# when left moves:
# if word in visited:
# visited[word] -= 1

# [how to check whether it's satisfied]
# if len(visited) equals len(count)
# and each value of `visited` is greather than each value of `count` 
# then it's satisfied

class Solution:
    def findSubstring(self, s: str, words: List[str]) -> List[int]:
        count = Counter(words)
        wl = len(words[0])

        answer = []

        for i in range(wl):
            left, right = i, i
            visited = {}
            temp = []

            while right <= len(s) - wl:
                w = s[right:right + wl]

                print(left, right, visited, count)

                if w in count:
                    visited[w] = visited.get(w, 0) + 1
                    if len(visited) == len(count) and all(
                        [visited.get(key, 0) >= count[key] for key in count]):
                        temp.append(left)
                        ww = s[left:left + wl]
                        if ww in visited:  # useless probably
                            visited[ww] -= 1
                            if visited[ww] == 0:
                                del visited[ww]
                        left += wl
                else:
                    while left != right:
                        ww = s[left:left + wl]
                        if ww in visited:  # useless probably
                            visited[ww] -= 1
                            if visited[ww] == 0:
                                del visited[ww]
                        left += wl
                    left += wl

                right += wl

                over = right - left + 1 > len(words) * wl
                while over > 0:
                    ww = s[left:left + wl]
                    if ww in visited:  # useless probably
                        visited[ww] -= 1
                        if visited[ww] == 0:
                            del visited[ww]
                    left += wl
                    over -= wl

            answer.extend(temp)

        return answer

        # count = Counter(words)
        # wl, num_words = len(words[0]), len(words)
        # total_words_len = wl * num_words
        # answer = []
        #
        # for i in range(wl):
        #     visited = defaultdict(int)
        #     for j in range(i, len(s) - wl + 1, wl):
        #         w = s[j:j + wl]
        #
        #         if w in count:
        #             visited[w] += 1
        #
        #         if j >= total_words_len:
        #             ww = s[j - total_words_len:j - total_words_len + wl]
        #             if ww in count:
        #                 visited[ww] -= 1
        #
        #         if visited == count:
        #             answer.append(j + wl - total_words_len)
        #
        # return answer
