import java.util.Scanner;

public class Main {

    private static final String BLANK = " ";
    private static final String STAR = "*";
    private static boolean[][] answer;

    private static void solution(int n, int y, int x) {
        if (n == 3) {
            answer[y-1][x-1] = true;
            answer[y][x-2] = answer[y][x] = true;
            answer[y+1][x-3] = answer[y+1][x-2] = answer[y+1][x-1] = answer[y+1][x] = answer[y+1][x+1] = true;
            return;
        }

        int next = n / 2;
        solution(next, y, x);
        solution(next, y+next, x-next);
        solution(next, y+next, x+next);
    }

    private static void printAnswer(int x, int y) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                if (!answer[i][j]) {
                    sb.append(BLANK);
                    continue;
                }
                sb.append(STAR);
            }
            sb.append("\n");
        }
        System.out.println(sb.toString().stripTrailing());
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.close();

        int count = Math.round(n/3);
        int x = count * 5 + count - 1;
        int y = n;
        answer = new boolean[y][x];

        solution(n, 1, n);

        printAnswer(x, y);
    }
}