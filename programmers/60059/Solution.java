class Solution {

    private int getBoardLength(int m, int n) {
        return n + 2 * m - 2;
    }

    private void initializeBoard(int[][] board, int[][] lock, int m, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[m-1+i][m-1+j] = lock[i][j];
            }
        }
    }

    private boolean isCorrectKey(int[][] board, int m, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[m-1+i][m-1+j] != 1) {
                    return false;
                }
            }
        }
        return true;
    }

    private void compareKey(int[][] board, int[][] key, int m, int rowIndex, int columnIndex) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                board[i+rowIndex][j+columnIndex] += key[i][j];
            }
        }
    }

    private void resetBoard(int[][] board, int[][] key, int m, int rowIndex, int columnIndex) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                board[i+rowIndex][j+columnIndex] -= key[i][j];
            }
        }
    }

    private int[][] rotateRight(int[][] key) {
        int m = key.length;
        int[][] rotated = new int[m][m];
        for (int i = 0; i < rotated.length; i++) {
            for (int j = 0; j < rotated.length; j++) {
                rotated[i][j] = key[m-1-j][i];
            }
        }
        return rotated;
    }

    public boolean solution(int[][] key, int[][] lock) {
        int m = key.length;
        int n = lock.length;
        int boardLength = getBoardLength(m, n);
        int[][] board = new int[boardLength][boardLength];
        initializeBoard(board, lock, m, n);

        for (int i = 0; i < 4; i++) {
            key = rotateRight(key);
            for (int rowIndex = 0; rowIndex < m+n-1; rowIndex++) {
                for (int columnIndex = 0; columnIndex < m+n-1; columnIndex++) {
                    compareKey(board, key, m, rowIndex, columnIndex);
                    if (isCorrectKey(board, m, n)) {
                        return true;
                    }
                    resetBoard(board, key, m, rowIndex, columnIndex);
                }
            }
        }
        return false;
    }
}