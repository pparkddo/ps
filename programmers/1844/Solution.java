import java.util.LinkedList;
import java.util.Queue;

class Solution {

    private static final int BLANK = 1;
    private static final int[] dr = {-1, 1, 0, 0};
    private static final int[] dc = {0, 0, -1, 1};

    public int solution(int[][] maps) {
        boolean[][] visited = new boolean[maps.length][maps[0].length];

        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0, 0, 1));
        visited[0][0] = true;

        int answer = -1;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int row = node.row;
            int column = node.column;
            int count = node.count;
            if (row == maps.length-1 && column == maps[0].length-1) {
                answer = count;
                break;
            }
            for (int d = 0; d < dr.length; d++) {
                int nextRow = row + dr[d];
                int nextColumn = column + dc[d];
                if (!isValid(nextRow, nextColumn, maps)) {
                    continue;
                }
                if (visited[nextRow][nextColumn]) {
                    continue;
                }
                if (maps[nextRow][nextColumn] != BLANK) {
                    continue;
                }
                queue.add(new Node(nextRow, nextColumn, count+1));
                visited[nextRow][nextColumn] = true;
            }
        }

        return answer;
    }

    private static boolean isValid(int row, int column, int[][] maps) {
        return row >= 0 && row < maps.length && column >= 0 && column < maps[0].length;
    }
}

class Node {

    int row;
    int column;
    int count;

    Node(int row, int column, int count) {
        this.row = row;
        this.column = column;
        this.count = count;
    }
}