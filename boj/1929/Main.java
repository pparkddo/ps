import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

    private static boolean[] getEratos(int n) {
        boolean[] eratos = new boolean[n+1]; 
        Arrays.fill(eratos, true);

        if (n >= 0) {
            eratos[0] = false;
        }
        if (n >= 1) {
            eratos[1] = false;
        }
        for (int i = 2; (i*i) <= n; i++) {
            if (eratos[i]) {
                for (int j = (i*i); j <= n; j+=i) {
                    eratos[j] = false;
                }
            }
        }
        return eratos;
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        in.close();

        StringTokenizer st = new StringTokenizer(s, " ");
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        boolean[] eratos = getEratos(n);

        StringBuilder answer = new StringBuilder();
        for (int number = m; number <= n; number++) {
            if (eratos[number]) {
                answer.append(number).append("\n");
            }
        }
        System.out.println(answer);
    }
}