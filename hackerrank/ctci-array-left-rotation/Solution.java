import java.util.ArrayList;
import java.util.List;

public class Solution {

    static int[] rotLeft(int[] a, int d) {
        List<Integer> answer = new ArrayList<>();
        for (int index = d; index < a.length; index++) {
            answer.add(a[index]);
        }
        for (int index = 0; index < d; index++) {
            answer.add(a[index]);            
        }
        return answer.stream().mapToInt(i -> i).toArray();
    }
}
