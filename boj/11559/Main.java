import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

public class Main {

    private static final int ROW_COUNT = 12;
    private static final int COLUMN_COUNT = 6;
    private static final char BLANK = '.';
    private static final int[] dr  = {-1, 1, 0, 0};
    private static final int[] dc  = {0, 0, -1, 1};
    private static final int SEQUENCE_COUNT = 4;

    private static int solution(char[][] board) {
        int count = 0;
        while (true) {
            if (!explode(board)) {
                break;
            }
            count++;
            fall(board);
        }
        return count;
    }

    private static void fall(char[][] board) {
        for (int column = 0; column < board[0].length; column++) {
            fallColumn(board, column);
        }
    }

    private static void fallColumn(char[][] board, int column) {
        List<Character> blocks = new ArrayList<>();
        for (int i = board.length-1; i >= 0; i--) {
            if (board[i][column] == BLANK) {
                continue;
            }
            blocks.add(board[i][column]);
            board[i][column] = BLANK;
        }
        int row = board.length - 1;
        for (Character block : blocks) {
            board[row--][column] = block;
        }
    }

    private static boolean explode(char[][] board) {
        boolean[][] visited = new boolean[board.length][board[0].length];
        List<Block> explodeBlocks = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == BLANK) {
                    continue;
                }
                if (visited[i][j]) {
                    continue;
                }
                getExplodeBlock(i, j, board, visited).ifPresent(each -> explodeBlocks.add(each));
            }
        }
        for (Block block : explodeBlocks) {
            explode(block, board);
        }
        return !explodeBlocks.isEmpty();
    }

    private static void explode(Block block, char[][] board) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(block.row, block.column));
        char value = board[block.row][block.column];
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            final int row = node.row;
            final int column = node.column;
            for (int d = 0; d < dr.length; d++) {
                int nextRow = row + dr[d];
                int nextColumn = column + dc[d];
                if (!isValid(nextRow, nextColumn, board)) {
                    continue;
                }
                if (board[nextRow][nextColumn] != value) {
                    continue;
                }
                queue.add(new Node(nextRow, nextColumn));
                board[nextRow][nextColumn] = BLANK;
            }
        }
    }

    private static Optional<Block> getExplodeBlock(int initialRow, int initialColumn,
             char[][] board, boolean[][] visited) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(initialRow, initialColumn));
        visited[initialRow][initialColumn] = true;

        char value = board[initialRow][initialColumn];
        int maxCount = -1;
        int count = 0;
        Optional<Block> result = Optional.empty();
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            final int row = node.row;
            final int column = node.column;
            count++;
            if (count >= SEQUENCE_COUNT && count > maxCount) {
                maxCount = count;
                result = Optional.of(new Block(row, column));
            }
            for (int d = 0; d < dr.length; d++) {
                int nextRow = row + dr[d];
                int nextColumn = column + dc[d];
                if (!isValid(nextRow, nextColumn, board)) {
                    continue;
                }
                if (board[nextRow][nextColumn] != value) {
                    continue;
                }
                if (visited[nextRow][nextColumn]) {
                    continue;
                }
                queue.add(new Node(nextRow, nextColumn));
                visited[nextRow][nextColumn] = true;
            }
        }

        return result;
    }

    private static boolean isValid(int row, int column, char[][] board) {
        return row >= 0 && row < board.length && column >= 0 && column < board[0].length;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        char[][] board = new char[ROW_COUNT][COLUMN_COUNT];
        for (int i = 0; i < ROW_COUNT; i++) {
            String each = in.readLine();
            for (int j = 0; j < COLUMN_COUNT; j++) {
                board[i][j] = each.charAt(j);
            }
        }
        in.close();
        System.out.println(solution(board));
    }
}

class Node {

    int row;
    int column;

    Node(int row, int column) {
        this.row = row;
        this.column = column;
    }
}

class Block {

    int row;
    int column;

    Block(int row, int column) {
        this.row = row;
        this.column = column;
    }
}
