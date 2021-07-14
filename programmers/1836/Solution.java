import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

class Solution {

    private static final char BLANK = '.';
    private static final char WALL = '*';

    public String solution(int m, int n, String[] brd) {
        char[][] board = getBoard(brd);
        StringBuilder answer = new StringBuilder();
        while (true) {
            List<Candidate> candidates = getCandidates(board);
            if (candidates.isEmpty()) {
                break;
            }
            Collections.sort(candidates, (a, b) -> Character.compare(a.end.value, b.end.value));
            Candidate candidate = candidates.get(0);
            board[candidate.start.row][candidate.start.column] = BLANK;
            board[candidate.end.row][candidate.end.column] = BLANK;
            answer.append(candidate.start.value);
        }
        return canRemoveAllTiles(board) ? answer.toString() : "IMPOSSIBLE";
    }

    private List<Candidate> getCandidates(char[][] board) {
        List<Candidate> candidates = new ArrayList<>();
        char minValue = 'Z' + 1;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (!Character.isAlphabetic(board[i][j])) {
                    continue;
                }
                if (board[i][j] > minValue) {
                   continue; 
                }
                Optional<Tile> removableTile = removeTile(i, j, board);
                if (removableTile.isPresent()) {
                    minValue = board[i][j];
                    candidates.add(new Candidate(new Tile(i, j, board[i][j]), removableTile.get()));
                }
            }
        }
        return candidates;
    }

    private boolean canRemoveAllTiles(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (Character.isAlphabetic(board[i][j])) {
                    return false;
                }
            }
        }
        return true;
    }

    private Optional<Tile> removeTile(int initialRow, int initialColumn, char[][] board) {
        char value = board[initialRow][initialColumn];

        Queue<Node> queue = new LinkedList<>();
        boolean[][][] visited = new boolean[board.length][board[0].length][2];
        visited[initialRow][initialColumn][0] = true;
        visited[initialRow][initialColumn][1] = true;
        pass(initialRow, initialColumn+1, 0, visited, board, DIRECTION.HORIZONTAL, queue, value);
        pass(initialRow, initialColumn-1, 0, visited, board, DIRECTION.HORIZONTAL, queue, value);
        pass(initialRow+1, initialColumn, 0, visited, board, DIRECTION.VERTICAL, queue, value);
        pass(initialRow-1, initialColumn, 0, visited, board, DIRECTION.VERTICAL, queue, value);

        Optional<Tile> result = Optional.empty();
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            final int row = node.row;
            final int column = node.column;
            final DIRECTION direction = node.direction;
            final int turnCount = node.turnCount;
            if (board[row][column] == value) {
                result = Optional.of(new Tile(row, column, board[row][column]));
                break;
            }
            if (direction == DIRECTION.HORIZONTAL) {
                pass(row, column+1, turnCount, visited, board, DIRECTION.HORIZONTAL, queue, value);
                pass(row, column-1, turnCount, visited, board, DIRECTION.HORIZONTAL, queue, value);
                pass(row+1, column, turnCount+1, visited, board, DIRECTION.VERTICAL, queue, value);
                pass(row-1, column, turnCount+1, visited, board, DIRECTION.VERTICAL, queue, value);
            } else {
                pass(row, column+1, turnCount+1, visited, board, DIRECTION.HORIZONTAL, queue, value);
                pass(row, column-1, turnCount+1, visited, board, DIRECTION.HORIZONTAL, queue, value);
                pass(row+1, column, turnCount, visited, board, DIRECTION.VERTICAL, queue, value);
                pass(row-1, column, turnCount, visited, board, DIRECTION.VERTICAL, queue, value);
            }
        }
        return result;
    }

    private boolean canPass(int row, int column, int turnCount, boolean[][][] visited, char[][] board, char value) {
        if (turnCount > 1) {
            return false;
        }
        if (!isValid(row, column, board)) {
            return false;
        }
        if (visited[row][column][turnCount]) {
            return false;
        }
        if (board[row][column] == WALL) {
            return false;
        }
        if (board[row][column] == value) {
            return true;
        }
        return board[row][column] == BLANK;
    }

    private void pass(int row, int column, int turnCount, boolean[][][] visited,
            char[][] board, DIRECTION direction, Queue<Node> queue, char value) {
        if (!canPass(row, column, turnCount, visited, board, value)) {
            return;
        }
        queue.add(new Node(row, column, direction, turnCount));
        visited[row][column][turnCount] = true;
    }

    private boolean isValid(int row, int column, char[][] board) {
        return row >= 0 && row < board.length && column >= 0 && column < board[0].length;
    }

    private char[][] getBoard(String[] brd) {
        char[][] board = new char[brd.length][brd[0].length()];
        for (int i = 0; i < brd.length; i++) {
            String each = brd[i];
            char[] cs = each.toCharArray();
            for (int j = 0; j < cs.length; j++) {
                board[i][j] = cs[j];
            }
        }
        return board;
    }
}

class Node {

    int row;
    int column;
    DIRECTION direction;
    int turnCount;

    Node(int row, int column, DIRECTION direction, int turnCount) {
        this.row = row;
        this.column = column;
        this.direction = direction;
        this.turnCount = turnCount;
    }
}

class Tile {

    int row;
    int column;
    char value;

    Tile(int row, int column, char value) {
        this.row = row;
        this.column = column;
        this.value = value;
    }
}

class Candidate {

    Tile start;
    Tile end;

    Candidate(Tile start, Tile end) {
        this.start = start;
        this.end = end;
    }
}

enum DIRECTION {

    HORIZONTAL,
    VERTICAL,
}