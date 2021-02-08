import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(in.readLine());
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(in.readLine());
            int[] zeroCount = new int[n+1];
            int[] oneCount = new int[n+1];
            if (n >= 0) {
                zeroCount[0] = 1;
                oneCount[0] = 0;
            }
            if (n >= 1) {
                zeroCount[1] = 0;
                oneCount[1] = 1;
            }
            for (int index = 2; index < oneCount.length; index++) {
                zeroCount[index] = zeroCount[index-2] + zeroCount[index-1];
                oneCount[index] = oneCount[index-2] + oneCount[index-1];
            }
            answer.append(zeroCount[n]).append(" ").append(oneCount[n]).append("\n");
        }
        System.out.println(answer.toString().stripTrailing());
        in.close();
    }
}
