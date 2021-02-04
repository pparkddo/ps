import java.util.Scanner;

public class Main {

    private static int getDecomposition(int number) {
        int decomposition = number;
        while (number > 0) {
            decomposition = decomposition + number % 10;
            number = number / 10;
        }
        return decomposition;
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.close();
        int answer = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            if (n == getDecomposition(i)) {
                answer = Math.min(i, answer);
            }
        }
        System.out.println(answer == Integer.MAX_VALUE ? 0 : answer);
    }
}
