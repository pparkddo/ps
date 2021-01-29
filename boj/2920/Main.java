import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        in.close();

        List<Integer> scales = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(s, ", ");
        while (st.hasMoreTokens()) {
            scales.add(Integer.parseInt(st.nextToken()));
        }

        List<Integer> ascending = scales.stream().sorted().collect(Collectors.toList());
        if (scales.equals(ascending)) {
            System.out.println("ascending");
            return;
        };

        List<Integer> descending = scales.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        if (scales.equals(descending)) {
            System.out.println("descending");
            return;
        };

        System.out.println("mixed");
    }
}