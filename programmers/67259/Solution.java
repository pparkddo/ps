import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Node {

    int row;
    int column;
    int direction;
    int cost;

    Node(int row, int column, int direction, int cost) {
        this.row = row;
        this.column = column;
        this.direction = direction;
        this.cost = cost;
    }
}

class Solution {

    int[][] board;
    int n;
    int[][] costs;
    int answer = Integer.MAX_VALUE;
    private static int[] dr = {-1, 1, 0, 0};
    private static int[] dc = {0, 0, -1, 1};

    private int getCost(int direction, int previousDirection) {
        if (previousDirection == -1) {
            return 100;
        }
        return direction == previousDirection ? 100 : 600;
    }

    private void bfs() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0, 0, -1, 0));
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.row == n-1 && node.column == n-1) {
                answer = Math.min(answer, node.cost);
                continue;
            }
            if (costs[node.row][node.column] < node.cost) {
                continue;
            }
            costs[node.row][node.column] = node.cost;
            for (int direction = 0; direction < dr.length; direction++) {
                int nextRow = node.row + dr[direction];
                int nextColumn = node.column + dc[direction];
                if (!isAvailable(nextRow, nextColumn)) {
                    continue;
                }
                if (board[node.row][node.column] == 1) {
                    continue;
                }
                int nextCost = node.cost + getCost(direction, node.direction);
                queue.add(new Node(nextRow, nextColumn, direction, nextCost));
            }
        }
    }

    private boolean isAvailable(int row, int column) {
        return (
            row >= 0
            && row < n
            && column >= 0
            && column < n
        );
    }

    public int solution(int[][] board) {
        this.board = board;
        this.n = board.length;
        this.costs = new int[n][n];
        for (int[] each : costs) {
            Arrays.fill(each, Integer.MAX_VALUE);
        }
        bfs();
        return answer;
    }
}
