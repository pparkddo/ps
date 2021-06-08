import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {

    private static final int WALL = 1;
    private static final int CANDIDATE = 2;
    private static final int[] dr = {-1, 1, 0, 0};
    private static final int[] dc = {0, 0, -1, 1};

    private static int solution(int n, int m, int[][] lab) {
        List<Candidate> candidates = getCandidates(lab);
        int answer = dfs(0, 0, m, new boolean[candidates.size()], lab, candidates, Integer.MAX_VALUE);
        if (answer == Integer.MAX_VALUE) {  // unreachable
            return -1;
        }
        if (answer == Integer.MIN_VALUE) {  // need no reach
            return 0;
        }
        return answer;
    }

    private static List<Candidate> getCandidates(int[][] lab) {
        List<Candidate> candidates = new ArrayList<>();
        for (int i = 0; i < lab.length; i++) {
            for (int j = 0; j < lab[0].length; j++) {
                if (lab[i][j] == CANDIDATE) {
                    candidates.add(new Candidate(i, j));
                }
            }
        }
        return candidates;
    }

    private static int dfs(int start, int depth, int m, boolean[] visited, int[][] lab, List<Candidate> candidates, int minDistance) {
        if (depth == m) {
            int[][] distances = new int[lab.length][lab[0].length];
            for (int row = 0; row < distances.length; row++) {
                Arrays.fill(distances[row], Integer.MAX_VALUE);
            }
            for (int index = 0; index < visited.length; index++) {
                if (visited[index]) {
                    Candidate candidate = candidates.get(index);
                    spread(candidate.row, candidate.column, distances, lab);
                }
            }
            return getMaxDistance(distances, lab);
        }
        for (int index = start; index < visited.length; index++) {
            visited[index] = true;
            minDistance = Math.min(minDistance, dfs(index+1, depth+1, m, visited, lab, candidates, minDistance));
            visited[index] = false;
        }
        return minDistance;
    }

    private static int getMaxDistance(int[][] distances, int[][] lab) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < distances.length; i++) {
            for (int j = 0; j < distances[0].length; j++) {
                if (lab[i][j] == WALL || lab[i][j] == CANDIDATE) {
                    continue;
                }
                max = Math.max(max, distances[i][j]);
            }
        }
        return max;
    }

    private static void spread(int initialRow, int initialColumn, int[][] distances, int[][] lab) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(initialRow, initialColumn, 0));

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            final int row = node.row;
            final int column = node.column;
            final int count = node.count;
            distances[row][column] = count;
            for (int d = 0; d < dr.length; d++) {
                int nextRow = row + dr[d];
                int nextColumn = column + dc[d];
                if (!isValid(nextRow, nextColumn, lab)) {
                    continue;
                }
                if (lab[nextRow][nextColumn] == WALL) {
                    continue;
                }
                int nextCount = count + 1;
                if (nextCount >= distances[nextRow][nextColumn]) {
                    continue;
                }
                distances[nextRow][nextColumn] = nextCount;
                queue.add(new Node(nextRow, nextColumn, nextCount));
            }
        }
    }

    private static boolean isValid(int row, int column, int[][] lab) {
        return row >= 0 && row < lab.length && column >= 0 && column < lab[0].length;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = in.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);
        int[][] lab = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] each = in.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                lab[i][j] = Integer.parseInt(each[j]);
            }
        }
        in.close();
        System.out.println(solution(n, m, lab));
    }
}

class Candidate {

    int row;
    int column;

    Candidate(int row, int column) {
        this.row = row;
        this.column = column;
    }

    @Override
    public String toString() {
        return row + " " + column;
    }
}

class Node {

    int row;
    int column;
    int count;

    Node(int row, int column, int count) {
        this.row = row;
        this.column = column;
        this.count = count;
    }
}