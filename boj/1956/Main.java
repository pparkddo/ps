import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static final int UNREACHABLE = 400 * 10_000 + 1;

    private static int solution(int v, int e, int[][] distances) {
        for (int k = 1; k <= v; k++) {
            for (int i = 1; i <= v; i++) {
                for (int j = 1; j <= v; j++) {
                    distances[i][j] = Math.min(distances[i][j], distances[i][k] + distances[k][j]);
                }
            }
        }
        int answer = UNREACHABLE;
        for (int i = 1; i <= v; i++) {
            answer = Math.min(answer, distances[i][i]);
        }
        return answer == UNREACHABLE ? -1 : answer;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] ve = in.readLine().split(" ");
        int v = Integer.parseInt(ve[0]);
        int e = Integer.parseInt(ve[1]);
        int[][] distances = new int[v+1][v+1];
        for (int i = 1; i <= v; i++) {
            Arrays.fill(distances[i], UNREACHABLE);
        }
        for (int i = 0; i < e; i++) {
            String[] abc = in.readLine().split(" ");
            int a = Integer.parseInt(abc[0]);
            int b = Integer.parseInt(abc[1]);
            int c = Integer.parseInt(abc[2]);
            distances[a][b] = Math.min(distances[a][b], c);
        }
        in.close();
        System.out.println(solution(v, e, distances));
    }
}
