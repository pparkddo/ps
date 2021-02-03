import java.util.Scanner;

public class Main {

    private static boolean[][] answer;

    private static void solution(int n, int x, int y) {
        if (n == 3) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (i == 1 && j == 1) {
                        continue;
                    }
                    answer[x+i][y+j] = true;
                }
            }
            return;
        }

        int nextN = n / 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 1 && j == 1) {
                    continue;
                }
                solution(nextN, x+nextN*i, y+nextN*j);
            }
        }
    }

    private static void printAnswer(int x, int y) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                if (!answer[i][j]) {
                    sb.append(" ");
                    continue;
                }
                sb.append("*");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString().stripTrailing());
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.close();

        answer = new boolean[n][n];

        solution(n, 0, 0);

        printAnswer(n, n);
    }
}
