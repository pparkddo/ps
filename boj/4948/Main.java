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

        for (int i = 2; (i * i) < eratos.length; i++) {
            for (int number = (i*i); number < eratos.length; number += i) {
                eratos[number] = true;
            }
        }

        return eratos;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder answer = new StringBuilder();

        while (true) {
            int n = Integer.parseInt(in.readLine());
            if (n == 0) {
                break;
            }
            boolean[] eratos = getEratos(2*n);
            int count = 0;
            for (int i = n+1; i < eratos.length; i++) {
                boolean isNotPrime = eratos[i];
                if (isNotPrime) {
                    continue;
                }
                count = count + 1;
            }
            answer.append(count).append("\n");
        }

        System.out.println(answer.toString().strip());

        in.close();
    }
}