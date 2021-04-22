import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

class Solution {

    private int convertOrderToBit(String order) {
        int b = 0;
        for (char each : order.toCharArray()) {
            int flag = each - 'A';
            b |= (1 << flag);
        }
        return b;
    }

    private List<Integer> getOrderBits(String[] orders) {
        return Arrays.stream(orders)
                     .map(each -> convertOrderToBit(each))
                     .collect(Collectors.toList());
    }

    private void addPossibleCourseBit(int count, int depth, int start, int courseBit, List<Integer> courseBits) {
        if (depth == count) {
            courseBits.add(courseBit);
            return;
        }
        for (int index = start; index < 26; index++) {
            addPossibleCourseBit(count, depth+1, index+1, courseBit | (1 << index), courseBits);
        }
    }

    private int getCourseCount(List<Integer> orderBits, int courseBit) {
        int count = 0;
        for (int each : orderBits) {
            if ((each & courseBit) == courseBit) {
                count++;
            }
        }
        return count;
    }

    private boolean hasNthBit(int bit, int n) {
        int b = bit & (1 << n);
        return b != 0;
    }

    private String convertBitToMenu(int bit) {
        StringBuilder menu = new StringBuilder();
        for (int index = 0; index < 26; index++) {
            if (hasNthBit(bit, index)) {
                char m = (char) (index + 'A');
                menu.append(m);
            }
        }
        return menu.toString();
    }

    private List<String> getMenus(int bitCount, List<Integer> orderBits) {
        Map<Integer, List<String>> counts = new HashMap<>();

        List<Integer> courseBits = new ArrayList<>();
        addPossibleCourseBit(bitCount, 0, 0, 0, courseBits);

        int max = -1;
        for (int courseBit : courseBits) {
            int courseCount = getCourseCount(orderBits, courseBit);
            if (courseCount >= 2) {
                max = Math.max(max, courseCount);
                List<String> menus = counts.getOrDefault(courseCount, new ArrayList<>());
                menus.add(convertBitToMenu(courseBit));
                counts.put(courseCount, menus);
            }
        }

        return counts.get(max);
    }

    public String[] solution(String[] orders, int[] course) {
        List<Integer> orderBits = getOrderBits(orders);
        List<String> answer = new ArrayList<>();
        for (int each : course) {
            List<String> menus = getMenus(each, orderBits);
            if (menus != null) {
                answer.addAll(menus);
            }
        }
        return answer.stream().sorted().toArray(String[]::new);
    }
}
