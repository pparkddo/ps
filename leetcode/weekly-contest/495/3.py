class Solution:
    def sortableIntegers(self, nums: list[int]) -> int:
        n = len(nums)
        result = 0

        divisors = []
        for i in range(1, int(n**0.5) + 1):
            if n % i == 0:
                divisors.append(i)
                if i != n // i:
                    divisors.append(n // i)

        for k in divisors:
            sortable = True
            prev_last = 0

            for start in range(0, n, k):
                descent_pos = -1
                descent_count = 0
                for j in range(k):
                    nxt = j + 1 if j + 1 < k else 0
                    if nums[start + j] > nums[start + nxt]:
                        descent_count += 1
                        descent_pos = j
                        if descent_count >= 2:
                            break

                if descent_count >= 2:
                    sortable = False
                    break

                if descent_count == 0:
                    first = nums[start]
                    last = nums[start]
                else:
                    r = descent_pos + 1 if descent_pos + 1 < k else 0
                    first = nums[start + r]
                    last = nums[start + descent_pos]

                if first < prev_last:
                    sortable = False
                    break

                prev_last = last

            if sortable:
                result += k

        return result
