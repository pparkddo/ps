class Solution {

    public int numIslands(char[][] grid) {
        int answer = 0;

        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column < grid[row].length; column++) {
                if (grid[row][column] == '1') {
                    dfs(grid, row, column);
                    answer++;
                }
            }
        }

        return answer;
    }

    private void dfs(char[][] grid, int row, int column) {
        if (row < 0 || grid.length <= row) {
            return;
        }
        if (column < 0 || grid[row].length <= column) {
            return;
        }
        if (grid[row][column] != '1') {
            return;
        }
        grid[row][column] = '-';
        dfs(grid, row - 1, column);
        dfs(grid, row + 1, column);
        dfs(grid, row, column - 1);
        dfs(grid, row, column + 1);
    }
}