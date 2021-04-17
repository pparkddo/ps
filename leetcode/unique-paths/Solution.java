class Solution {

    public int uniquePaths(int m, int n) {
        int sum = m + n - 2;
        int k = Math.min(m-1, n-1);

        // num C k
        double answer = 1;
        for (int i = 1; i <= k; i++) {
            answer *= (double) (sum-k+i) / (double) i;
        }
        return (int) Math.round(answer);
    }
}
