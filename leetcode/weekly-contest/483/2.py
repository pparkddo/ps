class Solution:

    def wordSquares(self, words: List[str]) -> List[List[str]]:
        words.sort()
        answer = []

        def dfs(depth: int, square: list[str], used: set[int]) -> None:
            if depth == 4:
                answer.append(square[:])
                return

            if depth == 0:
                for i, word in enumerate(words):
                    square.append(word)
                    used.add(i)
                    dfs(depth + 1, square, used)
                    used.remove(i)
                    square.pop()
            elif depth == 1:
                for i, word in enumerate(words):
                    if i in used or square[0][0] != word[0]:
                        continue
                    square.append(word)
                    used.add(i)
                    dfs(depth + 1, square, used)
                    used.remove(i)
                    square.pop()
            elif depth == 2:
                for i, word in enumerate(words):
                    if i in used or square[0][-1] != word[0]:
                        continue
                    square.append(word)
                    used.add(i)
                    dfs(depth + 1, square, used)
                    used.remove(i)
                    square.pop()
            elif depth == 3:
                for i, word in enumerate(words):
                    if i in used or square[1][-1] != word[0] or square[-1][-1] != word[-1]:
                        continue
                    square.append(word)
                    used.add(i)
                    dfs(depth + 1, square, used)
                    used.remove(i)
                    square.pop()

        dfs(0, [], set())

        return answer