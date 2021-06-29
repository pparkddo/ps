import java.util.LinkedList;
import java.util.Queue;

class Solution {

    private static final int WALL = 1;
    private static final int[] dr = {-1, 1, 0, 0};
    private static final int[] dc = {0, 0, -1, 1};

    public int solution(int[][] board) {
        return bfs(board);
    }

    private int bfs(int[][] board) {
        final int n = board.length - 1;

        boolean[][][][] visited = new boolean[board.length][board.length][board.length][board.length];
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0, 0, 0, 1, 0));

        int answer = -1;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            final int r1 = node.r1;
            final int c1 = node.c1;
            final int r2 = node.r2;
            final int c2 = node.c2;
            final int count = node.count;

            if ((r1 == n && c1 == n) || (r2 == n && c2 == n)) {
                answer = count;
                break;
            }

            for (int d = 0; d < dr.length; d++) {
                final int nextR1 = r1 + dr[d];
                final int nextC1 = c1 + dc[d];
                final int nextR2 = r2 + dr[d];
                final int nextC2 = c2 + dc[d];
                if (!isValid(nextR1, nextC1, board) || !isValid(nextR2, nextC2, board)) {
                    continue;
                }
                if (board[nextR1][nextC1] == WALL || board[nextR2][nextC2] == WALL) {
                    continue;
                }
                if (visited[nextR1][nextC1][nextR2][nextC2]
                        || visited[nextR2][nextC2][nextR1][nextC1]) {
                    continue;
                }
                visited[nextR1][nextC1][nextR2][nextC2] = true;
                visited[nextR2][nextC2][nextR1][nextC1] = true;
                queue.add(new Node(nextR1, nextC1, nextR2, nextC2, count+1));
            }

            for (int d = 0; d < dr.length; d++) {
                if (!isParallelMove(r1, c1, r2, c2, d)) {
                    continue;
                }
                if (!canMove(r1, c1, r2, c2, d, board)) {
                    continue;
                }
                
                int rotateR1 = r1 + dr[d];
                int rotateC1 = c1 + dc[d];
                if (visited[r1][c1][rotateR1][rotateC1] || visited[rotateR1][rotateC1][r1][c1]) {
                    continue;
                }
                visited[r1][c1][rotateR1][rotateC1] = true;
                visited[rotateR1][rotateC1][r1][c1] = true;
                queue.add(new Node(r1, c1, rotateR1, rotateC1, count+1));

                int rotateR2 = r2 + dr[d];
                int rotateC2 = c2 + dc[d];
                if (visited[r2][c2][rotateR2][rotateC2] || visited[rotateR2][rotateC2][r2][c2]) {
                    continue;
                }
                visited[r2][c2][rotateR2][rotateC2] = true;
                visited[rotateR2][rotateC2][r2][c2] = true;
                queue.add(new Node(r2, c2, rotateR2, rotateC2, count+1));
            }
        }

        return answer;
    }

    private boolean canMove(int r1, int c1, int r2, int c2, int direction, int[][] board) {
        final int nextR1 = r1 + dr[direction];
        final int nextC1 = c1 + dc[direction];
        final int nextR2 = r2 + dr[direction];
        final int nextC2 = c2 + dc[direction];
        if (!isValid(nextR1, nextC1, board) || !isValid(nextR2, nextC2, board)) {
            return false;
        }
        if (board[nextR1][nextC1] == WALL || board[nextR2][nextC2] == WALL) {
            return false;
        }
        return true;
    }

    private boolean isParallelMove(int r1, int c1, int r2, int c2, int direction) {
        if (isHorizontal(r1, r2)) {
            return direction == 0 || direction == 1;
        }
        if (isVertical(c1, c2)) {
            return direction == 2 || direction == 3;
        }
        throw new IllegalArgumentException("unknown state");
    }

    private boolean isHorizontal(int r1, int r2) {
        return r1 == r2;
    }

    private boolean isVertical(int c1, int c2) {
        return c1 == c2;
    }

    private boolean isValid(int row, int column, int[][] board) {
        return row >= 0 && row < board.length && column >= 0 && column < board[0].length;
    }
}

class Node {

    int r1;
    int c1;
    int r2;
    int c2;
    int count;

    Node(int r1, int c1, int r2, int c2, int count) {
        this.r1 = r1;
        this.c1 = c1;
        this.r2 = r2;
        this.c2 = c2;
        this.count = count;
    }
}