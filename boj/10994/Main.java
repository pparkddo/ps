import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    
    private static final char STAR = '*';

    private static int getLength(int n) {
        return 4 * n - 3;
    }

    private static void print(char[][] board, int count, int start) {
        if (count < 1) {
            return;
        }
        for (int i = 0; i < count; i++) {
            board[start][i+start] = STAR;
            board[start+count-1][i+start] = STAR;
        }
        for (int i = 0; i < count; i++) {
            board[i+start][start] = STAR;
            board[i+start][start+count-1] = STAR;
        }
        print(board, count-4, start+2);
    }

    private static String solution(int n) {
        int length = getLength(n);
        char[][] board = new char[length][length];
        print(board, length, 0);

        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                answer.append(board[i][j] == STAR ? STAR : " ");
            }
            answer.append("\n");
        }
        return answer.toString().trim();
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        in.close();
        System.out.println(solution(n));
    }
}
