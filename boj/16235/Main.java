import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Main {

    private static final int INITIAL_VALUE = 5;
    private static final int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
    private static final int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};

    private static boolean isAvailable(int row, int column, int[][] area) {
        return (
            row >= 0
            && row < area.length
            && column >= 0
            && column < area[0].length
        );
    }

    private static int solution(int n, int m, int k, int[][] a, List<List<Deque<Integer>>> trees) {
        int[][] area = new int[n][n];
        for (int i = 0; i < area.length; i++) {
            Arrays.fill(area[i], INITIAL_VALUE);
        }

        while (k-- > 0) {
            for (int row = 0; row < n; row++) {
                for (int column = 0; column < n; column++) {
                    Deque<Integer> values = trees.get(row).get(column);
                    int size = values.size();
                    int nutrient = 0;
                    for (int i = 0; i < size; i++) {
                        int value = values.pollFirst();
                        if (area[row][column] < value) {
                            nutrient += value / 2;
                            continue;
                        }
                        area[row][column] -= value;
                        values.addLast(value+1);
                    }
                    area[row][column] += nutrient;
                }
            }
            for (int row = 0; row < n; row++) {
                for (int column = 0; column < n; column++) {
                    Deque<Integer> values = trees.get(row).get(column);
                    for (int value : values) {
                        if (value % 5 != 0) {
                            continue;
                        }
                        for (int direction = 0; direction < dr.length; direction++) {
                            int r = row + dr[direction];
                            int c = column + dc[direction];
                            if (!isAvailable(r, c, area)) {
                                continue;
                            }
                            trees.get(r).get(c).addFirst(1);
                        }
                    }
                }
            }
            for (int row = 0; row < n; row++) {
                for (int column = 0; column < n; column++) {
                    area[row][column] += a[row][column];
                }
            }
        }

        int count = 0;
        for (int i = 0; i < area.length; i++) {
            for (int j = 0; j < area.length; j++) {
                count += trees.get(i).get(j).size();
            }
        }
        return count;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] nmk = in.readLine().split(" ");
        int n = Integer.parseInt(nmk[0]);
        int m = Integer.parseInt(nmk[1]);
        int k = Integer.parseInt(nmk[2]);
        int[][] a = new int[n][n];

        for (int i = 0; i < n; i++) {
            String[] each = in.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                a[i][j] = Integer.parseInt(each[j]);
            }
        }

        List<List<Deque<Integer>>> trees = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<Deque<Integer>> each = new ArrayList<>();
            trees.add(each);
            for (int j = 0; j < n; j++) {
                each.add(new LinkedList<>());
            }
        }

        for (int i = 0; i < m; i++) {
            String[] each = in.readLine().split(" ");
            int rowIndex = Integer.parseInt(each[0]) - 1;
            int columnIndex = Integer.parseInt(each[1]) - 1;
            int age = Integer.parseInt(each[2]);
            trees.get(rowIndex).get(columnIndex).add(age);
        }

        in.close();

        System.out.println(solution(n, m, k, a, trees));
    }
}
