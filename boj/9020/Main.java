import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static boolean[] getEratos(int n) {
        boolean[] eratos = new boolean[n+1];

        if (n > 0) {
            eratos[0] = true;
        }

        if (n > 1) {
            eratos[1] = true;
        }

        for (int i = 2; i < (i * i); i++) {
            for (int number = (i * i); number < eratos.length; number+=i) {
                eratos[number] = true;
            }
        }

        return eratos;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder answer = new StringBuilder();

        int t = Integer.parseInt(in.readLine());
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(in.readLine());
            boolean[] eratos = getEratos(n);
            for (int a = n/2; a >= 1; a--) {
                int b = n-a;
                if (!eratos[a] && !eratos[b]) {
                    answer.append(a).append(" ").append(b).append("\n");
                    break;
                }
            }
        }

        System.out.println(answer.toString().strip());

        in.close();
    }
}