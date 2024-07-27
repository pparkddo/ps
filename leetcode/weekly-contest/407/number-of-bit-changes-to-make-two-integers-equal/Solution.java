class Solution {

    public int minChanges(int n, int k) {
        int difference = n ^ k;

        int answer = 0;

        int v = 1;
        for (int i = 0; i < 31; i++) {
            if ((difference & v) != 0) {
                if ((n & v) == 0) {
                    return -1;
                } else {
                    answer++;
                }
            }
            v *= 2;
        }

        return answer;
    }
}