import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Point implements Comparable<Point> {

    int x;
    int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return this.x + " " + this.y;
    }

    @Override
    public int compareTo(Point o) {
        if (this.x > o.x) {
            return 1;
        }
        if (this.x < o.x) {
            return -1;
        }
        if (this.y > o.y) {
            return 1;
        }
        if (this.y < o.y) {
            return -1;
        }
        return 0;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int[] input = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            points.add(new Point(input[0], input[1]));
        }
        in.close();

        points.sort(Point::compareTo);

        StringBuilder answer = new StringBuilder();
        for (Point point : points) {
            answer.append(point.toString()).append("\n");
        }
        System.out.println(answer.toString().stripTrailing());
    }
}