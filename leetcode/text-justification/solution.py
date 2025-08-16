class Solution:
    def fullJustify(self, words: List[str], maxWidth: int) -> List[str]:
        def partition(max_width):
            result = []
            i = 0

            while i < len(words):
                temp = []
                l = 0
                while i < len(words) and l + len(words[i]) <= max_width:
                    temp.append(words[i])
                    l += len(words[i])
                    l += 1
                    i += 1
                result.append(temp)

            return result

        partitioned = partition(maxWidth)
        # print(partitioned)

        def fully_justify(words, max_width):
            gap = len(words) - 1
            total_word_length_without_gap = sum(map(len, words))
            extra_spaces = max_width - total_word_length_without_gap

            if gap == 0:
                return words[0] + (" " * extra_spaces)

            space_size = extra_spaces // gap
            remained = extra_spaces % gap

            result = words[0]
            for w in words[1:]:
                result += " " * space_size
                if remained:
                    result += " "
                    remained -= 1
                result += w
            return result


        def left_justify(words, max_width):
            result = words[0]
            for w in words[1:]:
                result += " "
                result += w
            result += " " * (max_width - len(result))
            return result

        answer = []
        for p in partitioned[:-1]:
            answer.append(fully_justify(p, maxWidth))
        answer.append(left_justify(partitioned[-1], maxWidth))

        return answer
