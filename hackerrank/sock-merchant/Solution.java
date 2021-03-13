import java.util.HashMap;
import java.util.Map;

public class Solution {

    static int sockMerchant(int n, int[] ar) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (int each : ar) {
            int count = counts.getOrDefault(each, 0) + 1;
            counts.put(each, count);
        }
        return counts.values().stream().map(each -> each / 2).mapToInt(i -> i).sum();
    }
}