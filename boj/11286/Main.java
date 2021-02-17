import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        Comparator<Integer> absComparator = new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2) {
                int diff = Math.abs(o1) - Math.abs(o2);
                if (diff == 0) {
                    return o1 - o2;
                }
                return diff;
            }
        };
        PriorityQueue<Integer> queue = new PriorityQueue<>(absComparator);
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int number = Integer.parseInt(in.readLine());
            if (number == 0 && !queue.isEmpty()) {
                answer.append(queue.poll()).append("\n");
                continue;
            }
            if (number == 0 && queue.isEmpty()) {
                answer.append(0).append("\n");
                continue;
            }
            queue.add(number);
        }
        System.out.println(answer.toString().stripTrailing());
        in.close();
    }
}
