import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

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

        List<Integer> diffs = new ArrayList<>();
        for (int index = 0; index < scales.size()-1; index++) {
            diffs.add(scales.get(index+1)-scales.get(index));
        }

        if (diffs.stream().allMatch(diff -> diff == 1)) {
            System.out.println("ascending");
            return;
        };

        if (diffs.stream().allMatch(diff -> diff == -1)) {
            System.out.println("descending");
            return;
        };

        System.out.println("mixed");
    }
}