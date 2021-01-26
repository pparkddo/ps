import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    private static int getRange(int[] sortedNumbers) {
        if (sortedNumbers.length < 2) {
            return 0;
        }
        return sortedNumbers[sortedNumbers.length-1] - sortedNumbers[0];
    }

    private static int getMode(int[] numbers) {
        Map<Integer, Integer> frequencies = new HashMap<>();
        List<Integer> candidates = new ArrayList<>();
        int max = -1;
        for (int number: numbers) {
            int frequency = frequencies.getOrDefault(number, 0) + 1;
            frequencies.put(number, frequency);
            if (frequency > max) {
                max = frequency;
            }
        }
        for (Map.Entry<Integer, Integer> each : frequencies.entrySet()) {
            if (each.getValue().equals(max)) {
                candidates.add(each.getKey());
            }
        }
        if (candidates.size() > 1) {
            candidates.sort(null);
            return candidates.get(1);
        }
        return candidates.get(0);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numbers[] = new int[Integer.parseInt(br.readLine())];
        int sum = 0;
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = Integer.parseInt(br.readLine());
            sum = sum + numbers[i];
        }
        br.close();

        Arrays.sort(numbers);

        System.out.println(Math.round((float)sum/numbers.length));
        System.out.println(numbers[numbers.length/2]);
        System.out.println(getMode(numbers));
        System.out.println(getRange(numbers));
    }
}