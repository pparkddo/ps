import java.util.Scanner;

public class Main {

    private static int count = 0;
    private static StringBuilder answer;

    private static void move(int from, int to) {
        count = count + 1;
        answer.append(from).append(" ").append(to).append("\n");
    }

    private static void solution(int n, int from, int to, int via) {
        if (n == 1) {
            move(from, to);
            return;
        }
        solution(n-1, from, via, to);
        move(from, to);
        solution(n-1, via, to, from);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.close();
        answer = new StringBuilder();
        solution(n, 1, 3, 2);
        System.out.println(answer.insert(0, "\n").insert(0, count).toString().stripTrailing());
    }
}