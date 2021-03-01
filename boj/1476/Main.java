import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] esm = in.readLine().split(" ");
        int e = Integer.parseInt(esm[0]);
        int s = Integer.parseInt(esm[1]);
        int m = Integer.parseInt(esm[2]);
        in.close();

        int answer = 1;
        int E = 1;
        int S = 1;
        int M = 1;
        while (e != E || s != S || m != M) {
            answer++;
            E = answer % 15 == 0 ? 15 : answer % 15;
            S = answer % 28 == 0 ? 28 : answer % 28;
            M = answer % 19 == 0 ? 19 : answer % 19;
        }
        System.out.println(answer);
    }
}
