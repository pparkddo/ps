import java.util.Scanner;

public class Main {

    private static final String STAR = "*";
    private static final String BLANK = " ";
    private static boolean[][] answer;

    private static boolean getInitMode(int n) {
        return n % 2 == 0 ? true : false;
    }

    private static int getSize(int n) {
        return (int) Math.pow(2, n) - 1;
    }

    private static int getX(int n) {
        int x = 0;
        for (int i = n; i > 1; i--) {
            x = x + (int) Math.pow(2, i);
        }
        return x + 1;
    }

    private static void solution(int n, int y, int center, boolean mode) {
        if (n == 0) {
            return;
        }

        int size = getSize(n);
        int step = mode ? -1 : 1;

        int i = 0;
        for (; i < size; i++) {
            answer[y+step*i][center+i] = true;
            answer[y+step*i][center-i] = true;
        }
        i -= 1;
        for (int j = center-i; j <= center+i; j++) {
            answer[y+step*i][j] = true;
        }

        int nextY = mode ? y - size + 2 : y + size - 2;

        solution(n-1, nextY, center, !mode);
    }

    private static void printAnswer(int x, int y) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < y; i++) {
            int lastStar = 0;
            for (int j = 0; j < x; j++) {
                if (answer[i][j]) {
                    lastStar = j;
                }
            }
            for (int j = 0; j <= lastStar; j++) {
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

        boolean mode = getInitMode(n);
        int x = getX(n);
        int center = x / 2;
        int size = getSize(n);
        answer = new boolean[size][x];
        
        if (mode) {
            solution(n, size-1, center, mode);
        }
        else {
            solution(n, 0, center, mode);
        }
        printAnswer(x, size);
    }
}
