class Solution {

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int answer = 0;

        int total = 0;
        int subtotal = 0;
        for (int i = 0; i < gas.length; i++) {
            var a = gas[i] - cost[i];
            total += a;
            subtotal += a;
            if (subtotal < 0) {
                answer = i + 1;
                subtotal = 0;
            }
        }

        if (total < 0) {
            return -1;
        }

        return answer;
    }
}