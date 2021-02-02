import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> xPoints = new ArrayList<>();
        List<Integer> yPoints = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            String[] input = in.readLine().split(" ");
            Integer x = Integer.valueOf(input[0]);
            Integer y = Integer.valueOf(input[1]);

            if (xPoints.contains(x)) {
                xPoints.remove(x);
            }
            else {
                xPoints.add(x);
            }

            if (yPoints.contains(y)) {
                yPoints.remove(y);
            }
            else {
                yPoints.add(y);
            }
        }
        System.out.printf("%d %d", xPoints.get(0), yPoints.get(0));
        in.close();
    }
}