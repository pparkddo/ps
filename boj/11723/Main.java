import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

class Set {

    private java.util.Set<Integer> set = new HashSet<>();

    public void add(int x) {
        set.add(x);
    }

    public void remove(int x) {
        set.remove(x);
    }

    public int check(int x) {
        return set.contains(x) ? 1 : 0;
    }

    public void toggle(int x) {
        if (set.contains(x)) {
            set.remove(x);
            return;
        }
        set.add(x);
    }

    public void all() {
        empty();
        for (int number = 1; number <= 20; number++) {
            set.add(number);
        }
    }

    public void empty() {
        set.clear();
    }
}

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int m = Integer.parseInt(in.readLine());
        Set set = new Set();
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < m; i++) {
            String[] each = in.readLine().split(" ");
            String command = each[0];
            if (command.equals("add")) {
                set.add(Integer.parseInt(each[1]));
                continue;
            }
            if (command.equals("remove")) {
                set.remove(Integer.parseInt(each[1]));
                continue;
            }
            if (command.equals("check")) {
                answer.append(set.check(Integer.parseInt(each[1]))).append("\n");
                continue;
            }
            if (command.equals("toggle")) {
                set.toggle(Integer.parseInt(each[1]));
                continue;
            }
            if (command.equals("all")) {
                set.all();
                continue;
            }
            if (command.equals("empty")) {
                set.empty();
                continue;
            }
        }
        System.out.println(answer.toString().trim());
        in.close();
    }
}
