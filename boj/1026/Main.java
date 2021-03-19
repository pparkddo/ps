import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        int[] a = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] b = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        in.close();

        a = Arrays.stream(a).sorted().toArray();
        b = Arrays.stream(b).boxed().sorted(Collections.reverseOrder()).mapToInt(Integer::intValue).toArray();

        int s = 0;
        for (int i = 0; i < n; i++) {
            s += a[i] * b[i];
        }
        System.out.println(s);
    }
}
