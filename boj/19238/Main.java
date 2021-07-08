import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;

public class Main {

    private static final int BLANK = 0;
    private static final int WALL = 1;
    private static final int CUSTOMER = 2;
    private static final int[] dr = {-1, 1, 0, 0};
    private static final int[] dc = {0, 0, -1, 1};

    private static int solution(int n, int m, int fuel, int[][] map,
                                int r, int c, Map<Point, Destination> destinations) {
        TaxiStatus status = new TaxiStatus(r, c, fuel);

        int completeCustomerCount = 0;
        while (true) {
            if (!driveToCustomer(n, map, status)) {
                break;
            }
            if (!driveToDestination(n, map, status, destinations)) {
                break;
            }
            completeCustomerCount++;
        }
        return m == completeCustomerCount ? status.fuel : -1;
    }

    private static boolean driveToCustomer(int n, int[][] map, TaxiStatus status) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(status.row, status.column, 0));

        boolean[][] visited = new boolean[n][n];
        List<Candidate> candidates = new ArrayList<>();
        int minDistance = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            final int row = node.row;
            final int column = node.column;
            final int distance = node.distance;
            if (visited[row][column]) {
                continue;
            }
            if (map[row][column] == CUSTOMER) {
                minDistance = distance;
                candidates.add(new Candidate(row, column, distance));
            }
            visited[row][column] = true;
            if (distance >= minDistance) {
                continue;
            }
            for (int d = 0; d < dr.length; d++) {
                int nextRow = row + dr[d];
                int nextColumn = column + dc[d];
                if (!isValid(nextRow, nextColumn, map)) {
                    continue;
                }
                if (map[nextRow][nextColumn] == WALL) {
                    continue;
                }
                queue.add(new Node(nextRow, nextColumn, distance+1));
            }
        }

        Optional<Candidate> candidate = candidates.stream().sorted().findFirst();
        if (candidate.isEmpty()) {
            return false;
        }

        Candidate customer = candidate.get();
        if (customer.distance > status.fuel) {
            return false;
        }

        status.row = customer.row;
        status.column = customer.column;
        status.fuel -= customer.distance;
        map[status.row][status.column] = BLANK;

        return true;
    }
    
    private static boolean driveToDestination(int n, int[][] map, TaxiStatus status, 
                                            Map<Point, Destination> destinations) {
        Destination destination = destinations.get(new Point(status.row, status.column));

        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(status.row, status.column, 0));

        Optional<Node> destinationCandidate = Optional.empty();
        boolean[][] visited = new boolean[n][n];
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            final int row = node.row;
            final int column = node.column;
            final int distance = node.distance;
            if (row == destination.row && column == destination.column) {
                destinationCandidate = Optional.of(node);
                break;
            }
            if (visited[row][column]) {
                continue;
            }
            visited[row][column] = true;
            for (int d = 0; d < dr.length; d++) {
                int nextRow = row + dr[d];
                int nextColumn = column + dc[d];
                if (!isValid(nextRow, nextColumn, map)) {
                    continue;
                }
                if (map[nextRow][nextColumn] == WALL) {
                    continue;
                }
                queue.add(new Node(nextRow, nextColumn, distance+1));
            }
        }

        if (destinationCandidate.isEmpty()) {
            return false;
        }

        Node destinationInfo = destinationCandidate.get();
        if (destinationInfo.distance > status.fuel) {
            return false;
        }

        status.row = destinationInfo.row;
        status.column = destinationInfo.column;
        status.fuel += destinationInfo.distance;

        return true;
    }

    private static boolean isValid(int row, int column, int[][] map) {
        return row >= 0 && row < map.length && column >= 0 && column < map[0].length;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] nmf = in.readLine().split(" ");
        int n = Integer.parseInt(nmf[0]);
        int m = Integer.parseInt(nmf[1]);
        int fuel = Integer.parseInt(nmf[2]);
        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] each = in.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(each[j]);
            }
        }
        String[] rc = in.readLine().split(" ");
        int r = Integer.parseInt(rc[0]) - 1;
        int c = Integer.parseInt(rc[1]) - 1;
        Map<Point, Destination> destinations = new HashMap<>();
        for (int i = 0; i < m; i++) {
            String[] each = in.readLine().split(" ");
            int srcRow = Integer.parseInt(each[0]) - 1;
            int srcColumn = Integer.parseInt(each[1]) - 1;
            int destRow = Integer.parseInt(each[2]) - 1;
            int destColumn = Integer.parseInt(each[3]) - 1;
            destinations.put(new Point(srcRow, srcColumn), new Destination(destRow, destColumn));
            map[srcRow][srcColumn] = CUSTOMER;
        }
        in.close();
        System.out.println(solution(n, m, fuel, map, r, c, destinations));
    }
}

class Point {

    int row;
    int column;

    Point(int row, int column) {
        this.row = row;
        this.column = column;
    }

    @Override
    public boolean equals(Object obj) {
        Point o = (Point) obj;
        return row == o.row & column == o.column;
    }

    @Override
    public int hashCode() {
        int c = 31;

        int result = Integer.hashCode(row);
        result = result + c * Integer.hashCode(column);

        return result;
    }
}

class Destination {

    int row;
    int column;

    Destination(int row, int column) {
        this.row = row;
        this.column = column;
    }
}
class Candidate implements Comparable<Candidate> {

    int row;
    int column;
    int distance;

    Candidate(int row, int column, int distance) {
        this.row = row;
        this.column = column;
        this.distance = distance;
    }

    @Override
    public int compareTo(Candidate o) {
        if (distance != o.distance) {
            return Integer.compare(distance, o.distance);
        }
        if (row != o.row) {
            return Integer.compare(row, o.row);
        }
        return Integer.compare(column, o.column);
    }
}

class Node {
    
    int row;
    int column;
    int distance;

    Node(int row, int column, int distance) {
        this.row = row;
        this.column = column;
        this.distance = distance;
    }
}

class TaxiStatus {

    int row;
    int column;
    int fuel;

    TaxiStatus(int row, int column, int fuel) {
        this.row = row;
        this.column = column;
        this.fuel = fuel;
    }

    @Override
    public String toString() {
        return row + " " + column + " " + fuel;
    }
}