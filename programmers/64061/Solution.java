import java.util.Stack;

class Solution {

    private static final int NOT_FOUND = -1;

    private int getDoll(int[][] board, int column) {
        for (int i = 0; i < board.length; i++) {
            int value = board[i][column];
            if (value != 0) {
                board[i][column] = 0;
                return value;
            }
        }
        return NOT_FOUND;
    }

    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        Stack<Integer> stack = new Stack<>();
        for (int move : moves) {
            int column = move - 1;
            int doll = getDoll(board, column);
            if (doll == NOT_FOUND) {
                continue;
            }
            if (stack.isEmpty()) {
                stack.push(doll);
                continue;
            }
            if (stack.peek() == doll) {
                stack.pop();
                answer += 2;
                continue;
            }
            stack.push(doll);
        }
        return answer;
    }
}
