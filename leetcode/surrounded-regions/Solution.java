class Solution {

    private final int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private char[][] board;

    public void solve(char[][] board) {
        this.board = board;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (i == 0 || j == 0 || i == board.length - 1 || j == board[i].length - 1) {
                    if (board[i][j] == 'O') {
                        mark(i, j);
                    }
                }
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != 'S') {
                    board[i][j] = 'X';
                } else if (board[i][j] == 'S'){
                    board[i][j] = 'O';
                }
            }
        }
    }

    private void mark(int row, int column) {
        if (board[row][column] == 'S') {
            return;
        }

        // mark as survived
        board[row][column] = 'S';

        for (int[] direction : directions) {
            int nextRow = row + direction[0];
            int nextColumn = column + direction[1];
            if (!isValid(nextRow, nextColumn)) {
                continue;
            }
            if (board[nextRow][nextColumn] == 'O') {
                mark(nextRow, nextColumn);
            }
        }
    }

    private boolean isValid(int row, int column) {
        return row >= 0 && row < board.length && column >= 0 && column < board[row].length;
    }
}
