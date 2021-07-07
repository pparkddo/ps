import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    private static int solution(int n, List<String> words) {
        Map<Character, Integer> counts = new HashMap<>();
        for (String word : words) {
            calculateCounts(word, counts);
        }

        int sum = 0;
        int number = 9;
        for (Integer count : getSortedCounts(counts)) {
            sum += count * number--;
        }
        return sum;
    }

    private static List<Integer> getSortedCounts(Map<Character, Integer> counts) {
        return counts.values().stream()
            .sorted(Collections.reverseOrder()).collect(Collectors.toList());
    }

    private static void calculateCounts(String word, Map<Character, Integer> counts) {
        char[] characters = word.toCharArray();
        for (int index = 0; index < characters.length; index++) {
            int multiplier = characters.length - index - 1;
            int count = (int) Math.pow(10, multiplier);
            char c = characters[index];
            counts.put(c, counts.getOrDefault(c, 0)+count);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        List<String> words = new ArrayList<>();
        for (int index = 0; index < n; index++) {
            words.add(in.readLine());
        }
        in.close();
        System.out.println(solution(n, words));
    }
}
