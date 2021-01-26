import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int p = in.nextInt();

        long answer = 1;
        for (int i = 1; i <= n; i++) {
            answer = answer * i % p;
        }
        System.out.println(answer);

        in.close();
    }
}