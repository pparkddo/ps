class Solution {

    private static final int WATER = 0;
    private static final int LAND = 1;
    private static final int[] dr = {-1, 1, 0, 0};
    private static final int[] dc = {0, 0, -1, 1};
    private int[][] grid;
    private boolean[][] visited;
    private int answer = 0;

    public int islandPerimeter(int[][] grid) {
        this.grid = grid;
        this.visited = new boolean[grid.length][grid[0].length];

        Land startPoint = getStartPoint();
        traverse(startPoint.row, startPoint.column);

        return answer;
    }

    private Land getStartPoint() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == LAND) {
                    return new Land(i, j);
                }
            }
        }
        throw new IllegalArgumentException("Land does not exists");
    }

    private void traverse(int row, int column) {
        visited[row][column] = true;
        for (int d = 0; d < dr.length; d++) {
            int nextRow = row + dr[d];
            int nextColumn = column + dc[d];
            if (!isValid(nextRow, nextColumn)) {
                answer++;
                continue;
            }
            if (grid[nextRow][nextColumn] == WATER) {
                answer++;
                continue;
            }
            if (visited[nextRow][nextColumn]) {
                continue;
            }
            traverse(nextRow, nextColumn);
        }
    }

    private boolean isValid(int row, int column) {
        return row >= 0 && row < grid.length && column >= 0 && column < grid[0].length;
    }
}

class Land {
    
    int row;
    int column;

    Land(int row, int column) {
        this.row = row;
        this.column = column;
    }
}