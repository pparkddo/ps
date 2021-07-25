import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Main {

    private static String solution(Map<String, Integer> map, int size) {
        StringBuilder answer = new StringBuilder();
        for (Iterator<String> iter = map.keySet().stream().sorted().iterator(); iter.hasNext();) {
            String key = iter.next();
            answer.append(key).append(" ").append(getRatio(map.get(key), size)).append("\n");
        }
        return answer.toString().trim();
    }

    private static String getRatio(Integer count, int size) {
        return String.format("%.4f", (double) count / size * 100);
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Map<String, Integer> map = new HashMap<>();
        int size = 0;
        String each = "";
        while ((each = in.readLine()) != null) {
            map.put(each, map.getOrDefault(each, 0)+1);
            size++;
        }
        in.close();
        System.out.println(solution(map, size));
    }
}
