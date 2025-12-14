class Solution:
    def reverseWords(self, s: str) -> str:
        vowels = {"a", "e", "i", "o", "u"}

        def count(word):
            result = 0
            for w in word:
                if w in vowels:
                    result += 1
            return result

        words = s.split()
        first_word = words[0]
        fc = count(first_word)

        answer = [first_word]
        for word in words[1:]:
            answer.append(" ")
            if count(word) == fc:
                answer.append(word[::-1])
            else:
                answer.append(word)

        return "".join(answer)

