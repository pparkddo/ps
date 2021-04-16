class Solution {

    char[][] board;
    String word;
    boolean[][] visited;
    boolean isFinished = false;
    private static int[] dr = {-1, 1, 0, 0};
    private static int[] dc = {0, 0, -1, 1};

    private boolean isAvailable(int row, int column) {
        return (
            row >= 0
            && row < board.length
            && column >= 0
            && column < board[0].length
        );
    }

    private void dfs(int row, int column, int charIndex) {
        if (word.charAt(charIndex) != board[row][column]) {
            return;
        }
        if (visited[row][column] || isFinished) {
            return;
        }
        visited[row][column] = true;
        if (charIndex == word.length()-1) {
            isFinished = true;
            return;
        }
        for (int direction = 0; direction < dr.length; direction++) {
            int nextRow = row + dr[direction];
            int nextColumn = column + dc[direction];
            if (!isAvailable(nextRow, nextColumn)) {
                continue;
            }
            dfs(nextRow, nextColumn, charIndex+1);
        }
        visited[row][column] = false;
    }

    public boolean exist(char[][] board, String word) {
        this.board = board;
        this.word = word;
        this.visited = new boolean[board.length][board[0].length];
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[0].length; column++) {
                if (!isFinished && board[row][column] == word.charAt(0)) {
                    dfs(row, column, 0);
                }
            }
        }
        return isFinished;
    }
}
