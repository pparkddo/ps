class Solution {

    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }

        int divider = Math.max(numRows + (numRows - 2), 1);
        int numColumns = (s.length() / divider + 1) * 2;
        char[][] board = new char[numRows][numColumns];

        boolean down = true;
        int row = 0;
        int column = 0;
        int index = 0;
        int l = s.length();

        while (index < l) {
            while (index < l && down) {
                if (row == numRows - 1) {
                    board[row][column] = s.charAt(index++);
                    column++;
                    row--;
                    down = false;
                    break;
                }
                board[row][column] = s.charAt(index++);
                row++;
            }

            while (index< l && !down) {
                if (row == 0) {
                    board[row][column] = s.charAt(index++);
                    column++;
                    row++;
                    down = true;
                    break;
                }
                if (row == 1) {
                    board[row][column] = s.charAt(index++);
                    column++;
                    row--;
                    down = true;
                    break;
                }
                board[row][column] = s.charAt(index++);
                row--;
            }
        }

        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == Character.MIN_VALUE) {
                    continue;
                }
                answer.append(board[i][j]);
            }
        }

        return answer.toString();
    }
}
