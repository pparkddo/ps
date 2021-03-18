import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        in.readLine();
        int[] takers = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        String[] bc = in.readLine().split(" ");
        int b = Integer.parseInt(bc[0]);
        int c = Integer.parseInt(bc[1]);
        in.close();

        long answer = 0;
        for (int taker : takers) {
            taker -= b;
            answer++;
            if (taker > 0) {
                answer += Math.ceil((double)taker/c);
            }
        }
        System.out.println(answer);
    }
}
