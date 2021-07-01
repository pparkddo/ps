import java.util.Arrays;

class Solution {

    private static final int MAX_VERTEX_COUNT = 200;
    private static final int MAX_COST = 100_000;
    private static final int UNREACHABLE = MAX_VERTEX_COUNT * MAX_COST + 1;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int[][] distances = getDistances(n, fares);
        computeMinimumDistances(n, distances);
        return getAnswer(n, s, a, b, distances);
    }

    private int getAnswer(int n, int s, int a, int b, int[][] distances) {
        int answer = Integer.MAX_VALUE;
        for (int via = 1; via <= n; via++) {
            answer = Math.min(answer, distances[s][via] + distances[via][a] + distances[via][b]);
        }
        return answer;
    }

    private int[][] getDistances(int n, int[][] fares) {
        int[][] distances = new int[n+1][n+1];
        for (int i = 0; i < distances.length; i++) {
            Arrays.fill(distances[i], UNREACHABLE);
            distances[i][i] = 0;
        }
        for (int[] fare : fares) {
            int c = fare[0];
            int d = fare[1];
            int f = fare[2];
            distances[c][d] = f;
            distances[d][c] = f;
        }
        return distances;
    }

    private void computeMinimumDistances(int n, int[][] distances) {
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    int via = distances[i][k] + distances[k][j];
                    distances[i][j] = Math.min(distances[i][j], via);
                }
            }
        }
    }
}