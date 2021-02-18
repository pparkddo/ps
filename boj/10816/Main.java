import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        Map<Integer, Integer> cards = new HashMap<>();
        int[] cs = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int index = 0; index < n; index++) {
            int card = cs[index];
            cards.put(card, cards.getOrDefault(card, 0)+1);
        }
        StringBuilder answer = new StringBuilder();
        int m = Integer.parseInt(in.readLine());
        int[] numbers = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int index = 0; index < m; index++) {
            int number = numbers[index];
            answer.append(cards.getOrDefault(number, 0)).append(" ");
        }
        System.out.println(answer);
        in.close();
    }
}
