import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Main {

    private static final char INSERT = 'I';
    private static final char DELETE = 'D';
    private static final int MAX = 1;
    private static final int MIN = -1;

    private static String solution(int k, List<String> commands) {
        DualPriorityQueue dualPriorityQueue = new DualPriorityQueue();
        for (String command : commands) {
            char c = command.charAt(0);
            Integer argument = Integer.parseInt(command.split(" ")[1]);
            if (c == INSERT) {
                dualPriorityQueue.add(argument);
            }
            if (c == DELETE && argument == MAX) {
                dualPriorityQueue.pollMax();
            }
            if (c == DELETE && argument == MIN) {
                dualPriorityQueue.pollMin();
            }
        }

        if (dualPriorityQueue.isEmpty()) {
            return "EMPTY";
        }

        return dualPriorityQueue.peekMax() + " " + dualPriorityQueue.peekMin();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(in.readLine());
        StringBuilder answers = new StringBuilder();
        for (int testCase = 0; testCase < t; testCase++) {
            int k = Integer.parseInt(in.readLine());
            List<String> commands = new ArrayList<>();
            for (int i = 0; i < k; i++) {
                String command = in.readLine();
                commands.add(command);
            }
            answers.append(solution(k, commands)).append("\n");
        }
        in.close();
        System.out.println(answers.toString().trim());
    }
}

class DualPriorityQueue {

    private TreeMap<Integer, Integer> pq = new TreeMap<>();

    public void add(Integer key) {
        pq.put(key, pq.getOrDefault(key, 0)+1);
    }

    public Integer peekMin() {
        return pq.firstKey();
    }

    public Integer peekMax() {
        return pq.lastKey();
    }

    public Integer pollMin() {
        if (isEmpty()) {
            return null;
        }
        int key = peekMin();
        int nextValue = pq.get(key) - 1;
        if (nextValue == 0) {
            pq.remove(key);
            return key;
        }
        pq.put(key, nextValue);
        return key;
    }

    public Integer pollMax() {
        if (isEmpty()) {
            return null;
        }
        int key = peekMax();
        int nextValue = pq.get(key) - 1;
        if (nextValue == 0) {
            pq.remove(key);
            return key;
        }
        pq.put(key, nextValue);
        return key;
    }

    public boolean isEmpty() {
        return pq.isEmpty();
    }
}