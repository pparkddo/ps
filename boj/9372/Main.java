import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(in.readLine());
        StringBuilder answers = new StringBuilder();
        for (int i = 0; i < t; i++) {
            String[] nm = in.readLine().split(" ");
            int m = Integer.parseInt(nm[1]);
            for (int j = 0; j < m; j++) {
                in.readLine();
            }
            answers.append(Integer.parseInt(nm[0])-1).append("\n");
        }
        in.close();
        System.out.println(answers.toString().trim());
    }
}
