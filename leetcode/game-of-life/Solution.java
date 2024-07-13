class Solution {

    public void gameOfLife(int[][] board) {
        int[][] nextBoard = new int[board.length][board[0].length];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                nextBoard[i][j] = getNextStatus(i, j, board);
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = nextBoard[i][j];
            }
        }
    }

    private int getNextStatus(int row, int column, int[][] board) {
        int count = getCount(row, column, board);
        if (count < 2) {
            return 0;
        }
        if (count == 3 && board[row][column] == 0) {
            return 1;
        }
        if (count == 2 || count == 3) {
            return board[row][column];
        }
        return 0;
    }

    private int getCount(int row, int column, int[][] board) {
        int count = 0;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                int nextRow = row + i;
                int nextColumn = column + j;

                if (isAvailable(nextRow, nextColumn, board)) {
                   count += board[nextRow][nextColumn];
                }
            }
        }

        return count;
    }

    private boolean isAvailable(int row, int column, int[][] board) {
        return row >= 0 && row < board.length && column >= 0 && column < board[0].length;
    }
}