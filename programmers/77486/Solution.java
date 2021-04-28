import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {

    private Map<String, List<String>> getTree(String[] enroll, String[] referral) {
        Map<String, List<String>> tree = new HashMap<>();
        for (String each: enroll) {
            tree.put(each, new ArrayList<>());
        }
        for (int index = 0; index < referral.length; index++) {
            tree.get(enroll[index]).add(referral[index]);
        }
        return tree;
    }

    private void updateResult(String name, int price, Map<String, Integer> result, Map<String, List<String>> tree) {
        int nextPrice = price / 10;
        int currentPrice = price - nextPrice;
        int p = result.getOrDefault(name, 0) + currentPrice;
        result.put(name, p);
        if (nextPrice == 0) {
            return;
        }
        for (String each : tree.getOrDefault(name, new ArrayList<>())) {
            updateResult(each, nextPrice, result, tree);
        }
    }

    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        Map<String, List<String>> tree = getTree(enroll, referral);
        Map<String, Integer> result = new HashMap<>();
        for (int index = 0; index < amount.length; index++) {
            updateResult(seller[index], amount[index]*100, result, tree);
        }
        return Arrays.stream(enroll)
                     .map(each -> result.getOrDefault(each, 0))
                     .mapToInt(Integer::valueOf)
                     .toArray();
    }
}
