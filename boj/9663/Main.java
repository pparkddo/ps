import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution {

    private int n;
    private boolean placed[][];
    private int count = 0;

    Solution(int n) {
        this.n = n;
        this.placed = new boolean[n][n];
    }

    public int getCount() {
        return count;
    }

    public void run() {
        for (int i = 0; i < this.n; i++) {
            dfs(0, i, 1);
        }
    }

    private void dfs(int row, int column, int depth) {
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.n; j++) {
                if (!placed[i][j]) {
                    continue;
                }
                if (isSameColumn(column, j)) {
                    return;
                }
                if (isLocatedDiagonally(row, column, i, j)) {
                    return;
                }
            }
        }
        if (depth == this.n) {
            count = count + 1;
            return;
        }
        for (int i = 0; i < this.n; i++) {
            placed[row][column] = true;
            dfs(row+1, i, depth+1);
            placed[row][column] = false;
        }
    }

    private boolean isSameColumn(int column, int otherColumn) {
        return column == otherColumn;
    }

    private boolean isLocatedDiagonally(int row, int column, int otherRow, int otherColumn) {
        return (
            row-column == otherRow-otherColumn
            || row+column == otherRow+otherColumn
        );
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        in.close();

        Solution solution = new Solution(n);
        solution.run();
        System.out.println(solution.getCount());
    }
}
