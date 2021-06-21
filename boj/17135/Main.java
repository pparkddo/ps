import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main {

    private static final int ENEMY = 1;
    private static final int NUMBER_OF_ARCHER = 3;

    private static int solution(int n, int m, int d, int[][] board) {
        List<Enemy> enemies = getEnemies(board);
        return dfs(0, 0, enemies, new boolean[m], n, d);
    }

    private static int dfs(int start, int depth, List<Enemy> enemies, boolean[] positions, int n, int d) {
        if (depth == NUMBER_OF_ARCHER) {
            return simulate(enemies, positions, n, d);
        }
        int count = 0;
        for (int index = start; index < positions.length; index++) {
            positions[index] = true;
            count = Math.max(count, dfs(index+1, depth+1, enemies, positions, n, d));
            positions[index] = false;
        }
        return count;
    }

    private static int simulate(List<Enemy> enemies, boolean[] positions, int n, int d) {
        int count = 0;
        int excludeCount = 0;
        List<Integer> positionIndices = getPositionIndices(positions);
        while (excludeCount < enemies.size()) {
            List<Enemy> enemiesToBeExcluded = new ArrayList<>();
            for (Integer index : positionIndices) {
                Optional<Enemy> enemy = getMinDistanceEnemy(enemies, index, n, d);
                enemy.ifPresent(it -> enemiesToBeExcluded.add(it));
            }
            for (Enemy enemy : enemiesToBeExcluded) {
                if (enemy.isExcluded) {
                    continue;
                }
                enemy.isExcluded = true;
                count++;
                excludeCount++;
            }
            enemies.stream().forEach(each -> each.down());
            for (Enemy each : enemies) {
                if (!each.isExcluded && each.row >= n) {
                    each.isExcluded = true;
                    excludeCount++;
                }
            }
        }
        enemies.stream().forEach(each -> each.reset());
        return count;
    }

    private static Optional<Enemy> getMinDistanceEnemy(List<Enemy> enemies, int index, int n, int d) {
        Enemy enemy = null;
        int minColumn = Integer.MAX_VALUE;
        int minDistance = Integer.MAX_VALUE;
        for (Enemy each : enemies) {
            if (each.isExcluded) {
                continue;
            }
            int distance = getDistance(n, index, each.row, each.column);
            if (d < distance) {
                continue;
            }
            if (enemy == null
                    || distance < minDistance
                    || (distance == minDistance && each.column < minColumn)) {
                enemy = each;
                minColumn = each.column;
                minDistance = distance;
            }
        }
        return Optional.ofNullable(enemy);
    }

    private static List<Integer> getPositionIndices(boolean[] positions) {
        List<Integer> indices = new ArrayList<>();
        for (int index = 0; index < positions.length; index++) {
            if (positions[index]) {
                indices.add(index);
            }
        }
        return indices;
    }

    private static int getDistance(int r1, int c1, int r2, int c2) {
        return Math.abs(r1-r2) + Math.abs(c1-c2);
    }

    private static List<Enemy> getEnemies(int[][] board) {
        List<Enemy> enemies = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == ENEMY) {
                    enemies.add(new Enemy(i, j));
                }
            }
        }
        return enemies;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] nmd = in.readLine().split(" ");
        int n = Integer.parseInt(nmd[0]);
        int m = Integer.parseInt(nmd[1]);
        int d = Integer.parseInt(nmd[2]);
        int[][] board = new int[n][m];
        for (int i = 0; i < n; i++) {
            String[] each = in.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(each[j]);
            }
        }
        in.close();
        System.out.println(solution(n, m, d, board));
    }
}

class Enemy {

    private final int initialRow;
    private final int initialColumn;
    int row;
    int column;
    boolean isExcluded = false;

    Enemy(int row, int column) {
        this.row = row;
        this.column = column;
        this.initialRow = row;
        this.initialColumn = column;
    }

    public void reset() {
        this.row = initialRow;
        this.column = initialColumn;
        this.isExcluded = false;
    }

    public void down() {
        this.row += 1;
    }

    @Override
    public String toString() {
        return this.row + " " + this.column;
    }
}
