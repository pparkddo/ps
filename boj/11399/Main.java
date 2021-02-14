import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        in.readLine();
        int[] minutes = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();
        in.close();

        int answer = 0;
        int accumulated = 0; 
        for (int minute : minutes) {
            accumulated += minute;
            answer += accumulated;
        }
        System.out.println(answer);
    }
}
