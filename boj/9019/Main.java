import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class CommandFinder {

    private static final int NOT_VISITED = -1;

    public String find(int start, int end) {
        return bfs(start, end);
    }

    private int calculateD(int n) {
        return n * 2 % 10000;
    }

    private int calculateS(int n) {
        if (n == 0) {
            return 9999;
        }
        return n - 1;
    }

    private int calculateL(int n) {
        return (n * 10 + n / 1000) % 10000;
    }

    private int calculateR(int n) {
        return n / 10 + ((n % 10) * 1000);
    }

    private String getAnswer(int[] parent, char[] command, int start, int end) {
        StringBuilder answer = new StringBuilder();
        while (end != start) {
            answer.append(command[end]);
            end = parent[end];
        }
        return answer.reverse().toString();
    }

    private String bfs(int start, int end) {
        int[] parent = new int[10000];
        Arrays.fill(parent, NOT_VISITED);
        char[] command = new char[10000];

        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            int number = queue.poll();
            if (number == end) {
                break;
            }

            int d = calculateD(number);
            int s = calculateS(number);
            int l = calculateL(number);
            int r = calculateR(number);

            if (parent[d] == NOT_VISITED) {
                parent[d] = number;
                command[d] = 'D';
                queue.add(d); 
            }
            if (parent[s] == NOT_VISITED) {
                parent[s] = number;
                command[s] = 'S';
                queue.add(s); 
            }
            if (parent[l] == NOT_VISITED) {
                parent[l] = number;
                command[l] = 'L';
                queue.add(l); 
            }
            if (parent[r] == NOT_VISITED) {
                parent[r] = number;
                command[r] = 'R';
                queue.add(r); 
            }
        }
        return getAnswer(parent, command, start, end);
    }
}

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(in.readLine());
        StringBuilder answers = new StringBuilder();
        CommandFinder commandFinder = new CommandFinder();
        for (int i = 0; i < t; i++) {
            String[] each = in.readLine().split(" ");
            int start = Integer.parseInt(each[0]);
            int end = Integer.parseInt(each[1]);
            answers.append(commandFinder.find(start, end)).append("\n");
        }
        in.close();

        System.out.println(answers.toString().trim());
    }
}
