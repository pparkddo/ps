import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static boolean[] eratos;

    private static boolean[] getEratos(int maxNumber) {
        boolean[] eratos = new boolean[maxNumber+1];

        eratos[0] = true;
        if (maxNumber >= 1) {
            eratos[1] = true;
        }

        for (int i = 2; (i*i) < eratos.length; i++) {
            for (int number = (i*i); number < eratos.length; number+=i) {
                eratos[number] = true;
            }
        }

        return eratos;
    }

    private static String solution(int n) {
        int number = 3;
        boolean isConjectureCorrect = false;
        while (number < n) {
            if (!eratos[number] && !eratos[n-number] && (n-number) != 2) {
                isConjectureCorrect = true;
                break;
            }
            number++;
        }
        return isConjectureCorrect ? getAnswerMessage(n, number) : "Goldbach's conjecture is wrong.";
    }

    private static String getAnswerMessage(int n, int number) {
        return n + " = " + number + " + " + (n-number);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answers = new StringBuilder();
        eratos = getEratos(1_000_000);
        while (true) {
            int n = Integer.parseInt(in.readLine());
            if (n == 0) {
                break;
            }
            answers.append(solution(n)).append("\n");
        }
        in.close();

        System.out.println(answers.toString().trim());
    }
}
