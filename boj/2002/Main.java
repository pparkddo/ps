import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    private static int solution(List<Integer> ranks) {
        int count = 0;
        for (int rank = 0; rank < ranks.size(); rank++) {
            if (isOvertaken(ranks, rank)) {
                count++;
                continue;
            }
        }
        return count;
    }

    private static boolean isOvertaken(List<Integer> ranks, int rank) {
        for (int i = rank+1; i < ranks.size(); i++) {
            if (ranks.get(rank) > ranks.get(i)) {
                return true;
            }
        }
        return false;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        Map<String, Integer> entrances = new HashMap<>();
        for (int i = 0; i < n; i++) {
            entrances.put(in.readLine(), i);
        }
        List<Integer> ranks = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            ranks.add(entrances.get(in.readLine()));
        }
        in.close();
        System.out.println(solution(ranks));
    }
}
