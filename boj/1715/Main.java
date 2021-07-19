import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

    private static int solution(int n, PriorityQueue<Integer> bundles) {
        int answer = 0;
        while (bundles.size() > 1) {
            int size = bundles.poll() + bundles.poll();
            bundles.add(size);
            answer += size;
        }
        return answer;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        PriorityQueue<Integer> bundles = new PriorityQueue<>();
        for (int index = 0; index < n; index++) {
            bundles.add(Integer.parseInt(in.readLine()));
        }
        in.close();
        System.out.println(solution(n, bundles));
    }
}
