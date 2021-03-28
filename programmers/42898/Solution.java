class Solution {
    
    int m;
    int n;
    int[][] dp;
    boolean[][] puddleMap;
    private static final int DIVISOR = 1_000_000_007;
    
    private int getWayCount(int row, int column) {
        if (row == m-1 && column == n-1) {
            return 1;
        }
        if (column >= n || row >= m) {
            return 0;
        }
        if (puddleMap[row][column]) {
            return 0;
        }
        if (dp[row][column] != 0) {
            return dp[row][column];
        }
        return dp[row][column] = (getWayCount(row, column+1) + getWayCount(row+1, column)) % DIVISOR;
    }
    
    public int solution(int m, int n, int[][] puddles) {
        this.m = m;
        this.n = n;
        this.dp = new int[m][n];
        this.puddleMap = new boolean[m][n];
        for (int[] each : puddles) {
            this.puddleMap[each[0]-1][each[1]-1] = true;
        }
        return getWayCount(0, 0);
    }
}
