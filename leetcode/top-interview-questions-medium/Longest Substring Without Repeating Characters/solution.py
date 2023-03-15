class Solution:

    def lengthOfLongestSubstring(self, s: str) -> int:
        if not s:
            return 0

        answer: int = 1

        left_index: int = 0
        right_index: int = 0
        contained_letter: set[s] = set()

        while left_index < len(s) and right_index < len(s):
            if left_index == right_index:
                right_index += 1
                continue

            left: str = s[left_index]
            right: str = s[right_index]

            contained_letter.add(left)

            if right in contained_letter:
                contained_letter.remove(left)
                left_index += 1
                continue

            answer = max(answer, right_index - left_index + 1)

            contained_letter.add(right)
            right_index += 1

        return answer
