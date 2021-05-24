import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final int[] dr = {-1, 1, 0, 0};
    private static final int[] dc = {0, 0, -1, 1};

    private static boolean isValid(int row, int column, int[][] counts) {
        return row >= 0 && row < counts.length && column >= 0 && column < counts[0].length;
    }

    private static boolean isOpen(int current, int next, int l, int r) {
        int diff = Math.abs(current-next);
        return diff >= l && diff <= r;
    }

    private static void dfs(int row, int column, boolean[][] visited, int[][] counts, int l, int r, List<Country> unions) {
        if (visited[row][column]) {
            return;
        }
        visited[row][column] = true;
        unions.add(new Country(row, column, counts[row][column]));
        for (int index = 0; index < dr.length; index++) {
            int nextRow = row + dr[index];
            int nextColumn = column + dc[index];
            if (!isValid(nextRow, nextColumn, counts)) {
                continue;
            }
            if (!isOpen(counts[row][column], counts[nextRow][nextColumn], l, r)) {
                continue;
            }
            dfs(nextRow, nextColumn, visited, counts, l, r, unions);
        }
    }

    private static int getUnionPopulation(List<Country> unions) {
        return unions.stream().mapToInt(each -> each.count).sum() / unions.size();
    }

    private static boolean union(int[][] counts, int l, int r) {
        boolean isUnionized = false;
        boolean[][] visited = new boolean[counts.length][counts[0].length];
        for (int row = 0; row < counts.length; row++) {
            for (int column = 0; column < counts[0].length; column++) {
                if (visited[row][column]) {
                    continue;
                }
                List<Country> unions = new ArrayList<>();
                dfs(row, column, visited, counts, l, r, unions);
                if (unions.size() != 1) {
                    isUnionized = true;
                }
                int unionPopulation = getUnionPopulation(unions);
                for (Country country : unions) {
                    counts[country.row][country.column] = unionPopulation;
                }
            }
        }
        return isUnionized;
    }

    private static int solution(int n, int l, int r, int[][] counts) {
        int answer = 0;
        while (union(counts, l, r)) {
            answer++;
        }
        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] nlr = in.readLine().split(" ");
        int n = Integer.parseInt(nlr[0]);
        int l = Integer.parseInt(nlr[1]);
        int r = Integer.parseInt(nlr[2]);
        int[][] counts = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] each = in.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                counts[i][j] = Integer.parseInt(each[j]);
            }
        }
        in.close();
        System.out.println(solution(n, l, r, counts));
    }
}

class Country {

    int row;
    int column;
    int count;

    Country(int row, int column, int count) {
        this.row = row;
        this.column = column;
        this.count = count;
    }

    @Override
    public String toString() {
        return row + " " + column + " " + count;
    }
}
