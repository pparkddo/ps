import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static boolean solution(String s, String k) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                continue;
            }
            sb.append(c);
        }
        s = sb.toString();

        int[] pi = getPi(k);
        for (int i = 0, j = 0; i < s.length(); i++) {
            while (j > 0 && s.charAt(i) != k.charAt(j)) {
                j = pi[j-1];
            }
            if (s.charAt(i) == k.charAt(j)) {
                if (j == k.length()-1) {
                    return true;
                } else {
                    j++;
                }
            }
        }
        return false;
    }

    private static int[] getPi(String k) {
        int[] pi = new int[k.length()];
        for (int i = 1, j = 0; i < k.length(); i++) {
            while (j > 0 && k.charAt(i) != k.charAt(j)) {
                j = pi[j-1];
            }
            if (k.charAt(i) == k.charAt(j)) {
                pi[i] = ++j;
            }
        }
        return pi;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String s = in.readLine();
        String k = in.readLine();
        in.close();
        System.out.println(solution(s, k) ? 1 : 0);
    }
}
