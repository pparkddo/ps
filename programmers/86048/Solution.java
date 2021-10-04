import java.util.HashSet;
import java.util.Set;

class Solution {

    private Set<Integer> room = new HashSet<>();
    private int[] answer;
    
    public int[] solution(int[] enter, int[] leave) {
        answer = new int[enter.length];

        int leaveIndex = 0;

        for (int enterPersonId : enter) {
            if (!room.isEmpty()) {
                calculate(enterPersonId);
            }
            room.add(enterPersonId);

            while (leaveIndex < leave.length && room.contains(leave[leaveIndex])) {
                room.remove(leave[leaveIndex]);
                leaveIndex++;
            }
        }
        
        return answer;
    }

    private void calculate(int enterPersonId) {
        for (Integer personId : room) {
            answer[convertIdToIndex(personId)]++;
            answer[convertIdToIndex(enterPersonId)]++;
        }
    }

    private int convertIdToIndex(int id) {
        return id - 1;
    }
}
