import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {

    private static String solution(BigInteger n, BigInteger m) {
        StringBuilder answer = new StringBuilder();
        answer.append(n.divide(m));
        answer.append("\n");
        answer.append(n.remainder(m));
        return answer.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = in.readLine().split(" ");
        BigInteger n = new BigInteger(nm[0]);
        BigInteger m = new BigInteger(nm[1]);
        in.close();
        System.out.println(solution(n, m));
    }
}
