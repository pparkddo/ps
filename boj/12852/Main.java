import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    private static final int NOT_VISITED = 0;

    private static int bfs(int n, int[] parents) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(n, 0));

        int minimumCount = 0;

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int number = node.number;
            int count = node.count;
            if (number == 1) {
                minimumCount = count;
                break;
            }
            if (number % 3 == 0 && parents[number/3] == NOT_VISITED) {
                queue.add(new Node(number/3, count+1));
                parents[number/3] = number;
            }
            if (number % 2 == 0 && parents[number/2] == NOT_VISITED) {
                queue.add(new Node(number/2, count+1));
                parents[number/2] = number;
            }
            if (parents[number-1] == NOT_VISITED) {
                queue.add(new Node(number-1, count+1));
                parents[number-1] = number;
            }
        }

        return minimumCount;
    }

    private static void trace(int[] parents, int number, StringBuilder routes) {
        int parent = parents[number];
        if (parent == NOT_VISITED) {
            return;
        }
        trace(parents, parent, routes);
        routes.append(number).append(" ");
    }

    private static String solution(int n) {
        StringBuilder answer = new StringBuilder();

        int[] parents = new int[n+1];
        int count = bfs(n, parents);

        StringBuilder routes = new StringBuilder();
        routes.append(n).append(" ");
        trace(parents, 1, routes);
        
        return answer.append(count).append("\n").append(routes).toString();
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        in.close();
        System.out.println(solution(n));
    }
}

class Node {
    
    int number;
    int count;

    Node(int number, int count) {
        this.number = number;
        this.count = count;
    }
}
