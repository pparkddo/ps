import java.util.LinkedList;
import java.util.Queue;

class Solution {

    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        boolean[] visited = new boolean[n * n + 1];

        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);

        for (int move = 0; !queue.isEmpty(); move++) {
            for (int size = queue.size(); size > 0; size--) {
                int num = queue.poll();
                if (visited[num]) {
                    continue;
                }
                visited[num] = true;
                if (num == n * n) {
                    return move;
                }
                for (int i = 1; i <= 6 && num + i <= n * n; i++) {
                    int next = num + i;
                    int value = getBoardValue(board, next);
                    if (value > 0) {
                        next = value;
                    }
                    if (!visited[next]) {
                        queue.add(next);
                    }
                }
            }
        }

        return -1;
    }

    private int getBoardValue(int[][] board, int num) {
        int n = board.length;
        int oldRow = (num - 1) / n;
        int row = n - 1 - oldRow;
        int oldCol = (num - 1) % n;
        int col = oldRow % 2 == 0 ? oldCol : n - 1 - oldCol;
        return board[row][col];
    }
}
