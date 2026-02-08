from collections import deque


class Solution:
    def countSubarrays(self, nums: List[int], k: int) -> int:
        max_q = deque()
        min_q = deque()

        n = len(nums)
        l = 0

        answer = 0
        for r in range(n):
            while max_q and nums[max_q[-1]] <= nums[r]:
                max_q.pop()
            max_q.append(r)
            
            while min_q and nums[min_q[-1]] >= nums[r]:
                min_q.pop()
            min_q.append(r)

            while l <= r:
                cur_max, cur_min  = nums[max_q[0]], nums[min_q[0]]
                cost = (cur_max - cur_min) * (r - l + 1)
                if cost <= k:
                    break
                
                if max_q[0] == l:
                    max_q.popleft()
                if min_q[0] == l:
                    min_q.popleft()

                l += 1

            answer += (r - l + 1)
        
        return answer