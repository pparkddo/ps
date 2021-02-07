import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

class Location {

    int x;
    int y;

    Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        Location location = (Location) obj;
        return this.x == location.x && this.y == location.y;
    }

    @Override
    public int hashCode() {
        return String.valueOf(this.x+this.y).hashCode();
    }
}

class Node {

    Location location;
    int count;

    Node(Location location, int count) {
        this.location = location;
        this.count = count;
    }

    public Location getLocation() {
        return location;
    }

    public int getCount() {
        return count;
    }
}

public class Main {

    private static int[][] maze;

    private static int bfs(int n, int m) {
        Queue<Node> queue = new LinkedList<>();
        Set<Location> visited = new HashSet<>();
        queue.add(new Node(new Location(0, 0), 1));
        int answer = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            Location location = node.getLocation();
            if (visited.contains(location)) {
                continue;
            }
            visited.add(location);
            int x = location.getX();
            int y = location.getY();
            int count = node.getCount();
            if (x == n && y == m) {
                answer = count;
                break;
            }
            if (x > 0 && maze[x-1][y] == 1) {
                queue.add(new Node(new Location(x-1, y), count+1));
            }
            if (x < n && maze[x+1][y] == 1) {
                queue.add(new Node(new Location(x+1, y), count+1));
            }
            if (y > 0 && maze[x][y-1] == 1) {
                queue.add(new Node(new Location(x, y-1), count+1));
            }
            if (y < m && maze[x][y+1] == 1) {
                queue.add(new Node(new Location(x, y+1), count+1));
            }
        }
        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = in.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);
        maze = new int[n][m];
        for (int i = 0; i < n; i++) {
            String[] input = in.readLine().split("");
            for (int j = 0; j < m; j++) {
                maze[i][j] = Integer.parseInt(input[j]);
            }
        }
        in.close();

        int count = bfs(n-1, m-1);
        System.out.println(count);
    }
}