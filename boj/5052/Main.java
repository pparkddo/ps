import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {

    private static String solution(int n, String[] numbers) {
        Set<String> container = new HashSet<>();
        for (String number : numbers) {
            container.add(number);
        }
        return isConsistent(container) ? "YES" : "NO";
    }

    private static boolean isConsistent(Set<String> container) {
        for (String number : container) {
            int digitLength = getDigitLength(number);
            for (int i = 1; i < digitLength; i++) {
                String n = number.substring(0, digitLength-i);
                if (container.contains(n)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static int getDigitLength(String number) {
        return number.length();
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answers = new StringBuilder();
        int t = Integer.parseInt(in.readLine());
        for (int testCase = 0; testCase < t; testCase++) {
            int n = Integer.parseInt(in.readLine());
            String[] numbers = new String[n];
            for (int index = 0; index < numbers.length; index++) {
                numbers[index] = in.readLine();
            }
            answers.append(solution(n, numbers)).append("\n");
        }
        in.close();
        System.out.println(answers.toString().trim());
    }
}
