import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static int[] gates;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int g = Integer.parseInt(in.readLine());
        int p = Integer.parseInt(in.readLine());

        gates = new int[g + 1];
        for (int i = 0; i < gates.length; i++) {
            gates[i] = i;
        }

        int answer = 0;
        for (int i = 0; i < p; i++) {
            int gate = Integer.parseInt(in.readLine());
            if (find(gate) == 0) {
                break;
            }
            answer++;
            union(find(gate), find(gate) - 1);
        }
        in.close();

        System.out.println(answer);
    }

    private static int find(int gate) {
        if (gates[gate] == gate) {
            return gate;
        }
        return gates[gate] = find(gates[gate]);
    }

    private static void union(int a, int b) {
        gates[find(a)] = find(b);
    }
}
