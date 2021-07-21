import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    private static final int NOT_VISITED = Integer.MAX_VALUE;
    private static final int[] dr = {-1, 1, 0, 0};
    private static final int[] dc = {0, 0, -1, 1};

    private static int solution(int n, int[][] cave) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0, 0, cave[0][0]));

        int[][] values = new int[n][n];
        for (int i = 0; i < values.length; i++) {
            Arrays.fill(values[i], NOT_VISITED);
        }

        int answer = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int row = node.row;
            int column = node.column;
            int value = node.value;
            if (values[row][column] < value) {
                continue;
            }
            if (row == n-1 && column == n-1) {
                answer = Math.min(answer, value);
            }
            for (int i = 0; i < dr.length; i++) {
                int nextRow = row + dr[i];
                int nextColumn = column + dc[i];
                if (!isValid(nextRow, nextColumn, cave)) {
                    continue;
                }
                int nextValue = value + cave[nextRow][nextColumn];
                if (values[nextRow][nextColumn] <= nextValue) {
                    continue;
                }
                values[nextRow][nextColumn] = nextValue;
                queue.add(new Node(nextRow, nextColumn, nextValue));
            }
        }

        return answer;
    }

    private static boolean isValid(int row, int column, int[][] cave) {
        return row >= 0 && row < cave.length && column >= 0 && column < cave[0].length;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answers = new StringBuilder();
        int problemNumber = 1;
        while (true) {
            int n = Integer.parseInt(in.readLine());
            if (n == 0) {
                break;
            }
            int[][] cave = new int[n][n];
            for (int i = 0; i < n; i++) {
                String[] each = in.readLine().split(" ");
                for (int j = 0; j < n; j++) {
                    cave[i][j] = Integer.parseInt(each[j]);
                }
            }
            answers.append("Problem ")
                .append(problemNumber++)
                .append(": ")
                .append(solution(n, cave))
                .append("\n");
        }
        in.close();
        System.out.println(answers.toString().trim());
    }
}

class Node {

    int row;
    int column;
    int value;

    Node(int row, int column, int value) {
        this.row = row;
        this.column = column;
        this.value = value;
    }
}