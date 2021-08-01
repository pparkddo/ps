import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    private static String solution(String s) {
        String[] groups = new String[8];
        Arrays.fill(groups, "");

        List<String> separated = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == ':') {
                separated.add(sb.toString());
                sb.setLength(0);
                continue;
            }
            sb.append(c);
        }
        separated.add(sb.toString());

        for (int index = 0; index < separated.size(); index++) {
            String each = separated.get(index);
            if (each.isBlank()) {
                break;
            }
            groups[index] = each;
        }

        for (int index = 0; index < separated.size(); index++) {
            String each = separated.get(separated.size()-index-1);
            if (each.isBlank()) {
                break;
            }
            groups[groups.length-index-1] = each;
        }

        return Arrays.stream(groups).map(each -> pad(each)).collect(Collectors.joining(":"));
    }

    private static String pad(String group) {
        if (group.length() == 4) {
            return group;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4 - group.length(); i++) {
            sb.append('0');
        }
        sb.append(group);
        return sb.toString();
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String s = in.readLine();
        in.close();
        System.out.println(solution(s));
    }
}
