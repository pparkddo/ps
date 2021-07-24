import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static boolean solution(String wave) {
        return wave.matches("(100+1+|01)+");
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(in.readLine());
        StringBuilder answers = new StringBuilder();
        for (int i = 0; i < t; i++) {
            answers.append(solution(in.readLine()) ? "YES" : "NO").append("\n");
        }
        in.close();
        System.out.println(answers.toString().trim());
    }
}
