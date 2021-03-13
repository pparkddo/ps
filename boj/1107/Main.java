import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    private static int n;
    private static int length;
    private static Set<Integer> buttons = new HashSet<>();
    private static int min = Integer.MAX_VALUE;
    private static final int INITIAL_CHANNEL = 100;

    private static int getLength(int n) {
        int length = 0;
        while (n != 0) {
            n /= 10;
            length++;
        }
        return length;
    }

    private static void dfs(int channel, int depth) {
        if (depth == length+1) {
            return;
        }
        for (Integer button : buttons) {
            int newChannel = Integer.parseInt(channel + "" + button);
            int count = Math.abs(n-newChannel) + depth + 1;
            min = Math.min(min, count);
            dfs(newChannel, depth+1);
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(in.readLine());
        int wrong_count = Integer.parseInt(in.readLine());
        int[] wrongs = {};
        if (wrong_count != 0) {
            wrongs = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        in.close();

        buttons = IntStream.rangeClosed(0, 9).boxed().collect(Collectors.toSet());
        for (int wrong : wrongs) {
            buttons.remove(wrong);
        }

        length = getLength(n);
        dfs(0, 0);

        min = Math.min(min, Math.abs(INITIAL_CHANNEL-n));
        System.out.println(min);
    }
}
