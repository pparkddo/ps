class Solution:
    def maxCapacity(self, costs: List[int], capacity: List[int], budget: int) -> int:
        answer = 0

        machines = sorted(zip(costs, capacity))
        for cost, cap in machines:
            if cost < budget:
                answer = max(answer, cap)
        # print(machines)

        max_left_cap = [machines[0][1]]
        for machine in machines[1:]:
            max_left_cap.append(max(max_left_cap[-1], machine[1]))
        # print(max_left_cap)

        for i in range(1, len(machines)):
            cost, cap = machines[i]

            left, right = 0, i -1
            found = -1

            while left <= right:
                mid = (left + right) // 2
                if machines[mid][0] < budget - cost:
                    found = mid
                    left = mid + 1
                else:
                    right = mid - 1

            if found != -1:
                answer = max(answer, max_left_cap[found] + cap)

        return answer
