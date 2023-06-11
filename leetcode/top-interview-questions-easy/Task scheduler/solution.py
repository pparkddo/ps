from collections import Counter


# https://leetcode.com/explore/interview/card/top-interview-questions-medium/114/others/826/discuss/104507/Python-Straightforward-with-Explanation
class Solution:

    def leastInterval(self, tasks: list[str], n: int) -> int:
        counts: list[int] = list(Counter(tasks).values())

        max_count: int = max(counts)

        number_of_chars_with_max_count: int = counts.count(max_count)

        number_of_chunks = max_count - 1
        length_of_each_chunk_with_idle: int = n + 1

        length_of_final_chunk: int = number_of_chars_with_max_count

        length_of_all_chunks: int = (number_of_chunks * length_of_each_chunk_with_idle) + length_of_final_chunk

        return max(len(tasks), length_of_all_chunks)
