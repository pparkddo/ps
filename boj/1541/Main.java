import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static final char PLUS = '+';
    private static final char MINUS = '-';

    private static void clearNumber(StringBuilder number) {
        number.setLength(0);
    }

    private static int toNumber(StringBuilder number) {
        return Integer.parseInt(number.toString());
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String expression = in.readLine();
        in.close();

        StringBuilder number = new StringBuilder();
        int isInParenthesis = 1;
        int answer = 0;
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == PLUS || c == MINUS) {
                answer = answer + toNumber(number) * isInParenthesis;
                clearNumber(number);
            }
            if (c == MINUS) {
                isInParenthesis = Math.min(isInParenthesis, -1);
                continue;
            }
            number.append(c);
        }
        System.out.println(answer + toNumber(number) * isInParenthesis);
    }
}
