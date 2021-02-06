import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Member implements Comparable<Member> {

    int index;
    int age;
    String name;

    Member(int index, int x, String y) {
        this.index = index;
        this.age = x;
        this.name = y;
    }

    @Override
    public String toString() {
        return this.age + " " + this.name;
    }

    @Override
    public int compareTo(Member o) {
        if (this.age > o.age) {
            return 1;
        }
        if (this.age < o.age) {
            return -1;
        }
        if (this.index > o.index) {
            return 1;
        }
        if (this.index < o.index) {
            return -1;
        }
        return 0;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        List<Member> points = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] input = in.readLine().split(" ");
            points.add(new Member(i, Integer.parseInt(input[0]), input[1]));
        }
        in.close();

        points.sort(Member::compareTo);

        StringBuilder answer = new StringBuilder();
        for (Member point : points) {
            answer.append(point.toString()).append("\n");
        }
        System.out.println(answer.toString().stripTrailing());
    }
}
