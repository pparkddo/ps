class Solution:
    def maxProduct(self, nums: List[int]) -> int:
        nums.sort()

        max_num = nums[-1]
        max_bit_len = max_num.bit_length()

        d = {}
        for num in nums:
            i = 0
            while i < max_bit_len:
                mask = (1 << i)
                if num & mask == 0:
                    d.setdefault(i, set()).add(num)
                i += 1
        print(d)

        answer = 0
        total = set(nums)

        possibles = {0: total}

        for num in nums:
            i = 0
            possible = total
            msb = 1 << (num.bit_length() - 1)
            if (num - msb) in possibles:
                possible = possibles[(num - msb)]
                i = num.bit_length() - 1

            while i < max_bit_len:
                mask = (1 << i)
                if num & mask != 0:
                    if i not in d:
                        possible = set()
                        break
                    possible = possible.intersection(d[i])
                i += 1

            possibles[num] = possible

            if not possible:
                continue

            max_possible = max(possible)
            answer = max(answer, num * max_possible)

        return answer