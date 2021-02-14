import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        while (true) {
            String[] ab = in.readLine().split(" ");
            int a = Integer.parseInt(ab[0]);
            int b = Integer.parseInt(ab[1]);
            if (a == 0 && b == 0) {
                in.close();
                break;
            }
            if (b % a == 0) {
                answer.append("factor").append("\n");
                continue;
            }
            if (a % b == 0) {
                answer.append("multiple").append("\n");
                continue;
            }
            answer.append("neither").append("\n");
        } 
        System.out.println(answer.toString().stripTrailing());
    }
}
