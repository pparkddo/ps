import java.util.Queue;
import java.util.LinkedList;

class Solution {

    private void mark(int rowIndex, int columnIndex, char[][] blocks, boolean[][] marked) {
        char block = blocks[rowIndex][columnIndex];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                char each = blocks[rowIndex+i][columnIndex+j];
                if (each == '-' || each != block) {
                    return;
                }
            }
        }
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                marked[rowIndex+i][columnIndex+j] = true;
            }
        }
    }

    private int deleteMarked(int m, int n, boolean[][] marked, char[][] blocks) {
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (marked[i][j]) {
                    blocks[i][j] = '-';
                    count++;
                }
            }
        }
        return count;
    }

    private int deleteBlock(int m, int n, char[][] blocks) {
        boolean[][] marked = new boolean[m][n];
        for (int i = 0; i < m - 1; i++) {
            for (int j = 0; j < n - 1; j++) {
                mark(i, j, blocks, marked);
            }
        }
        return deleteMarked(m, n, marked, blocks);
    }

    private void move(int column, char[][] blocks) {
        Queue<Character> queue = new LinkedList<>();
        int blankCount = 0;
        for (int row = 0; row < blocks.length; row++) {
            char each = blocks[row][column];
            if (each == '-') {
                blankCount++;
                continue;
            }
            queue.add(each);
        }
        for (int row = 0; row < blankCount; row++) {
            blocks[row][column] = '-';
        }
        for (int row = blankCount; row < blocks.length; row++) {
            blocks[row][column] = queue.poll();
        }
    }

    private void moveBlock(int m, int n, char[][] blocks) {
        for (int column = 0; column < n; column++) {
            move(column, blocks);
        }
    }

    public int solution(int m, int n, String[] board) {
        int answer = 0;
        char[][] blocks = new char[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                blocks[i][j] = board[i].charAt(j);
            }
        }

        while (true) {
            int count = deleteBlock(m, n, blocks);
            if (count == 0) {
                break;
            }
            moveBlock(m, n, blocks);
            answer += count;
        }
        return answer;
    }
}
