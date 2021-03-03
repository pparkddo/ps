import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Chicken {

    int r;
    int c;

    Chicken(int r, int c) {
        this.r = r;
        this.c = c;
    }
}

public class Main {

    private static final int HOUSE = 1;
    private static final int CHICKEN = 2;
    private static int[][] city;
    private static int[][][] distances;
    private static List<Chicken> chickens = new ArrayList<>();
    private static int[] selected;
    private static int answer = Integer.MAX_VALUE;

    private static int getChickenDistance(int ra, int ca, int rb, int cb) {
        return Math.abs(ra-rb) + Math.abs(ca-cb);
    }

    private static void setDistances() {
        int n = city.length;
        distances = new int[chickens.size()][n][n];
        for (int i = 0; i < chickens.size(); i++) {
            Chicken chicken = chickens.get(i);
            for (int r = 0; r < n; r++) {
                for (int c = 0; c < n; c++) {
                    int value = city[r][c];
                    if (value != HOUSE) {
                        continue;
                    }
                    distances[i][r][c] = getChickenDistance(r, c, chicken.r, chicken.c);
                }
            }
        }
    }

    private static int getChickenSum(int[] selected) {
        int n = city.length;
        int[][] minimumDistances = new int[n][n];
        for (int index : selected) {
            for (int r = 0; r < n; r++) {
                for (int c = 0; c < n; c++) {
                    if (minimumDistances[r][c] == 0) {
                        minimumDistances[r][c] = distances[index][r][c];
                        continue;
                    }
                    minimumDistances[r][c] = Math.min(minimumDistances[r][c], distances[index][r][c]);
                }
            }
        }
        int sum = 0;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                sum += minimumDistances[r][c];
            }
        }
        return sum;
    }

    private static void dfs(int depth, int start, int m) {
        if (depth == m) {
            answer = Math.min(answer, getChickenSum(selected));
            return;
        }
        for (int i = start; i < chickens.size(); i++) {
            selected[depth] = i;
            dfs(depth+1, i+1, m);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = in.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);

        city = new int[n][n];
        for (int r = 0; r < n; r++) {
            String[] each = in.readLine().split(" ");
            for (int c = 0; c < n; c++) {
                int value = Integer.parseInt(each[c]);
                city[r][c] = value;
                if (value == CHICKEN) {
                    chickens.add(new Chicken(r, c));
                }
            }
        }
        in.close();

        setDistances();

        selected = new int[m];
        dfs(0, 0, m);

        System.out.println(answer);
    }
}
