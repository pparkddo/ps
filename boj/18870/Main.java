import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    private static String solution(int n, int[] numbers) {
        int[] sorted = Arrays.stream(numbers).sorted().toArray();
        Map<Integer, Integer> counts = new HashMap<>();
        int id = 0;
        for (int each : sorted) {
            if (!counts.containsKey(each)) {
                counts.put(each, id++);
            }
        }
        return Arrays
                .stream(numbers)
                .boxed()
                .map(each -> String.valueOf(counts.get(each)))
                .collect(Collectors.joining(" "));
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        int[] numbers = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        in.close();

        System.out.println(solution(n, numbers));
    }
}
