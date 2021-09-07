import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Main {

    private static String solution(Point source, List<Point> points, Point destination) {
        Set<Point> visited = new HashSet<>();
        Queue<Point> queue = new LinkedList<>();
        queue.add(source);
        visited.add(source);
        boolean answer = false;
        while (!queue.isEmpty()) {
            Point point = queue.poll();
            if (point.equals(destination)) {
                answer = true;
                break;
            }
            for (Point each : points) {
                if (visited.contains(each)) {
                    continue;
                }
                if (!canGo(point, each)) {
                    continue;
                }
                queue.add(each);
                visited.add(each);
            }
        }
        return answer ? "happy" : "sad";
    }

    private static boolean canGo(Point from, Point to) {
        return getDistance(from, to) <= 1000;
    }

    private static int getDistance(Point from, Point to) {
        int x = Math.abs(from.x-to.x);
        int y = Math.abs(from.y-to.y);
        return x + y;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(in.readLine());
        StringBuilder answers = new StringBuilder();
        for (int testCase = 0; testCase < t; testCase++) {
            int n = Integer.parseInt(in.readLine());
            Point source = parsePoint(in.readLine().split(" "));
            List<Point> points = new ArrayList<>();
            for (int index = 0; index < n; index++) {
                points.add(parsePoint(in.readLine().split(" ")));
            }
            Point destination = parsePoint(in.readLine().split(" "));
            points.add(destination);
            answers.append(solution(source, points, destination)).append("\n");
        }
        in.close();
        System.out.println(answers.toString().trim());
    }

    private static Point parsePoint(String[] input) {
        int x = Integer.parseInt(input[0]);
        int y = Integer.parseInt(input[1]);
        return new Point(x, y);
    }
}

class Point {

    int x;
    int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        Point point = (Point) obj;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        int c = 31;
        int result = Integer.hashCode(x);
        result = c * result + Integer.hashCode(y);
        return result;
    }
}