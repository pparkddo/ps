import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static boolean isEnd(int a, int b, int c) {
        return a == 0 && b == 0 && c == 0;
    }

    private static boolean isRight(int a, int b, int c) {
        assert c >= a && c >= b; 
        return Math.pow(a, 2) + Math.pow(b, 2) == Math.pow(c, 2);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            int[] numbers = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::valueOf).sorted().toArray();

            int a = numbers[0];
            int b = numbers[1];
            int c = numbers[2];

            if (isEnd(a, b, c)) {
                break;
            }

            if (isRight(a, b, c)) {
                System.out.println("right");
            }
            else {
                System.out.println("wrong");
            }
        }
        in.close();
    }
}