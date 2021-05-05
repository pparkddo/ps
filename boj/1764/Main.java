import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {

    private static String solution(int n, int m, Set<String> notHeardPeople, Set<String> notSeenPeople) {
        StringBuilder answer = new StringBuilder();
        notHeardPeople.retainAll(notSeenPeople);
        answer.append(notHeardPeople.size()).append("\n");
        notHeardPeople.stream().sorted().forEach(each -> answer.append(each).append("\n"));
        return answer.toString().trim();
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = in.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);
        Set<String> notHeardPeople = new HashSet<>();
        Set<String> notSeenPeople = new HashSet<>();
        for (int i = 0; i < n; i++) {
            notHeardPeople.add(in.readLine());
        }
        for (int i = 0; i < m; i++) {
            notSeenPeople.add(in.readLine());
        }
        in.close();
        System.out.println(solution(n, m, notHeardPeople, notSeenPeople));
    }
}
