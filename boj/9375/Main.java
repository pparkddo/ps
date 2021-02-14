import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(in.readLine());
        StringBuilder answers = new StringBuilder();
        for (int i = 0; i < t; i++) {
            Map<String, List<String>> clothes = new HashMap<>();
            int n = Integer.parseInt(in.readLine());
            for (int j = 0; j < n; j++) {
                String[] each = in.readLine().split(" ");
                String type = each[1];
                List<String> items = clothes.getOrDefault(type, new ArrayList<>());
                items.add(each[0]);
                clothes.put(type, items);
            }
            List<Integer> sizes = clothes.entrySet()
                                         .stream()
                                         .map(each -> each.getValue().size())
                                         .collect(Collectors.toList());
            int answer = 1;
            for (Integer size: sizes) {
                answer *= (size + 1);
            }
            answers.append(answer-1).append("\n");
        }
        in.close();
        System.out.println(answers.toString().stripTrailing());
    }
}
