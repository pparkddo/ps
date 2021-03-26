import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Solution {

    private boolean isWrongElement(String element) {
        for (char each : element.toCharArray()) {
            if (!Character.isAlphabetic(each)) {
                return true;
            }
        }
        return false;
    }

    private List<String> getSeparated(String s) {
        List<String> separated = new ArrayList<>();
        for (int i = 0; i < s.length()-1; i++) {
            String element = s.substring(i, i+2);
            if (isWrongElement(element)) {
                continue;
            }
            separated.add(element);
        }
        return separated;
    }

    private Set<String> getElements(List<String> elementsA, List<String> elementsB) {
        Set<String> totalElements = new HashSet<>();
        totalElements.addAll(elementsA);
        totalElements.addAll(elementsB);
        return totalElements;
    }

    private Map<String, Integer> getCounts(List<String> elements) {
        Map<String, Integer> counts = new HashMap<>();
        for (String each : elements) {
            int count = counts.getOrDefault(each, 0) + 1;
            counts.put(each, count);
        }
        return counts;
    }

    private List<String> getIntersection(Set<String> elements, Map<String, Integer> countsA, Map<String, Integer> countsB) {
        List<String> intersection = new ArrayList<>();
        for (String element : elements) {
            int count = Math.min(countsA.getOrDefault(element, 0), countsB.getOrDefault(element, 0));
            for (int i = 0; i < count; i++) {
                intersection.add(element);
            }
        }
        return intersection;
    }

    private List<String> getUnion(Set<String> elements, Map<String, Integer> countsA, Map<String, Integer> countsB) {
        List<String> union = new ArrayList<>();
        for (String element : elements) {
            int count = Math.max(countsA.getOrDefault(element, 0), countsB.getOrDefault(element, 0));
            for (int i = 0; i < count; i++) {
                union.add(element);
            }
        }
        return union;
    }

    private int getJaccardSimilarity(List<String> intersection, List<String> union) {
        double value = (double) intersection.size() / union.size();
        if (intersection.isEmpty() && union.isEmpty()) {
            value = (double) 1;
        }
        return (int) (value * 65536);
    }

    public int solution(String str1, String str2) {
        str1 = str1.toUpperCase();
        str2 = str2.toUpperCase();

        List<String> elementsA = getSeparated(str1);
        List<String> elementsB = getSeparated(str2);

        Set<String> elements = getElements(elementsA, elementsB);

        Map<String, Integer> countsA = getCounts(elementsA);
        Map<String, Integer> countsB = getCounts(elementsB);

        List<String> intersection = getIntersection(elements, countsA, countsB);
        List<String> union = getUnion(elements, countsA, countsB);

        return getJaccardSimilarity(intersection, union);
    }
}
