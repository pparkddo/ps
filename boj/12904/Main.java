import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static boolean solution(String s, String t) {
        StringBuilder sb = new StringBuilder(t);
        while (sb.length() > s.length()) {
            char lastCharacter = sb.charAt(sb.length()-1);
            if (lastCharacter == 'A') {
                sb = deleteLastCharacter(sb);
            }
            else if (lastCharacter == 'B') {
                sb = deleteLastCharacter(sb).reverse();
            }
        }
        return sb.toString().equals(s);
    }

    private static StringBuilder deleteLastCharacter(StringBuilder sb) {
        return sb.deleteCharAt(sb.length()-1);
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String s = in.readLine();
        String t = in.readLine();
        in.close();
        System.out.println(solution(s, t) ? 1 : 0);
    }
}
