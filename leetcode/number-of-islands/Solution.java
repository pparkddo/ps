class Solution {
    
    private static final int WATER = '0';
    private static final int[] dr = {-1, 1, 0, 0};
    private static final int[] dc = {0, 0, -1, 1};
    
    public int numIslands(char[][] grid) {
        int answer = 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column < grid[0].length; column++) {
                if (grid[row][column] == WATER || visited[row][column]) {
                    continue;
                }
                dfs(row, column, grid, visited);
                answer++;
            }
        }
        return answer;
    }
    
    private boolean isAvailable(int row, int column, char[][] grid) {
        return (
            row >= 0
            && row < grid.length
            && column >= 0
            && column < grid[0].length
        );
    }
    
    private void dfs(int row, int column, char[][] grid, boolean[][] visited) {
        if (visited[row][column]) {
            return;
        }
        visited[row][column] = true;
        for (int i = 0; i < dr.length; i++) {
            int nextRow = row + dr[i];
            int nextColumn = column + dc[i];
            if (!isAvailable(nextRow, nextColumn, grid)) {
                continue;
            }
            if (grid[nextRow][nextColumn] == WATER) {
                continue;
            }
            dfs(nextRow, nextColumn, grid, visited);
        }
    }
}