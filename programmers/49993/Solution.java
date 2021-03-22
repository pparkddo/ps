import java.util.Map;
import java.util.HashMap;

class Solution {

    private Map<Character, Integer> getSkillOrder(String skill) {
        Map<Character, Integer> order = new HashMap<>();
        int index = 0;
        for (char each : skill.toCharArray()) {
            order.put(each, index++);
        }
        return order;
    }

    private boolean isPossibleSkillTree(Map<Character, Integer> skillOrder, String skillTree) {
        int current = 0;
        for (char each : skillTree.toCharArray()) {
            if (!skillOrder.containsKey(each)) {
                continue;
            }
            if (current != skillOrder.get(each)) {
                return false;
            }
            current++;
        }
        return true;
    }

    public int solution(String skill, String[] skill_trees) {
        Map<Character, Integer> skillOrder = getSkillOrder(skill);
        int answer = 0;
        for (String each : skill_trees) {
            if (isPossibleSkillTree(skillOrder, each)) {
                answer++;
            }
        }
        return answer;
    }
}
