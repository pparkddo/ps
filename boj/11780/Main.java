import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    private static final int UNREACHABLE = 100_000 * 100 + 1;

    private static void getRoute(int[][] routes, int source, int destination, Queue<Integer> route) {
        if (routes[source][destination] == destination) {
            route.add(source);
            return;
        }
        getRoute(routes, source, routes[source][destination], route);
        getRoute(routes, routes[source][destination], destination, route);
    }

    private static String getRoute(int[][] routes, int source, int destination) {
        Queue<Integer> route = new LinkedList<>();
        getRoute(routes, source, destination, route);
        route.add(destination);

        StringBuilder result = new StringBuilder();
        result.append(route.size()).append(" ");
        while (!route.isEmpty()) {
            result.append(route.poll()).append(" ");
        }

        return result.toString();
    }

    private static String solution(int n, int m, int[][] distances) {
        int[][] routes = new int[n+1][n+1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) {
                    routes[i][j] = UNREACHABLE;
                    continue;
                }
                routes[i][j] = distances[i][j] != UNREACHABLE ? j : UNREACHABLE;
            }
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    int via = distances[i][k] + distances[k][j];
                    if (via < distances[i][j]) {
                        distances[i][j] = via;
                        routes[i][j] = k;
                        continue;
                    }
                }
            }
        }

        StringBuilder answer = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                answer.append(distances[i][j] == UNREACHABLE ? 0 : distances[i][j]).append(" ");
            }
            answer.append("\n");
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (routes[i][j] == UNREACHABLE) {
                    answer.append(0);
                } else {
                    answer.append(getRoute(routes, i, j));
                }
                answer.append("\n");
            }
        }

        return answer.toString().trim();
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        int m = Integer.parseInt(in.readLine());
        int[][] distances = new int[n+1][n+1];
        for (int index = 0; index < distances.length; index++) {
            Arrays.fill(distances[index], UNREACHABLE);
            distances[index][index] = 0;
        }
        for (int i = 0; i < m; i++) {
            String[] abc = in.readLine().split(" ");
            int a = Integer.parseInt(abc[0]);
            int b = Integer.parseInt(abc[1]);
            int c = Integer.parseInt(abc[2]);
            distances[a][b] = Math.min(distances[a][b], c);
        }
        in.close();
        System.out.println(solution(n, m, distances));
    }
}
