import java.util.HashMap;
import java.util.Map;

class Solution {

    private static final int[] dr = {-1, 1, 0, 0};
    private static final int[] dc = {0, 0, -1, 1};
    private Map<Integer, Integer> sizes = new HashMap<>();
    private int numberOfArea = 0;
    private int maxSizeOfOneArea = 0;
    private int[][] picture;
    private boolean[][] visited;

    private boolean isAvailable(int row, int column) {
        return (
            row >= 0
            && row < picture.length
            && column >= 0
            && column < picture[0].length
        );
    }

    private void dfs(int row, int column, int id) {
        if (visited[row][column]) {
            return;
        }
        visited[row][column] = true;
        sizes.put(id, sizes.getOrDefault(id, 0) + 1);
        for (int direction = 0; direction < dr.length; direction++) {
            int nextRow = row + dr[direction];
            int nextColumn = column + dc[direction];
            if (!isAvailable(nextRow, nextColumn)) {
                continue;
            }
            if (picture[row][column] != picture[nextRow][nextColumn]) {
                continue;
            }
            dfs(nextRow, nextColumn, id);
        }
    }

    public int[] solution(int m, int n, int[][] picture) {
        this.picture = picture;
        visited = new boolean[m][n];
        for (int row = 0; row < m; row++) {
            for (int column = 0; column < n; column++) {
                if (picture[row][column] == 0) {
                    continue;
                }
                if (visited[row][column]) {
                    continue;
                }
                dfs(row, column, numberOfArea++);
            }
        }
        for (Integer color : sizes.keySet()) {
            maxSizeOfOneArea = Math.max(maxSizeOfOneArea, sizes.get(color));
        }
        return new int[] {numberOfArea, maxSizeOfOneArea};
    }
}
