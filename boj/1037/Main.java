import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        in.readLine();
        int[] factors = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();
        in.close();

        int answer = factors[0] * factors[factors.length-1];
        System.out.println(answer);
    }
}
