import java.util.HashSet;
import java.util.Set;

class Solution {

    Set<Integer> answer = new HashSet<>();
    boolean[] visited;

    private boolean isDifferentLength(String userId, String bannedId) {
        return userId.length() != bannedId.length();
    }

    private boolean isMatched(String userId, String bannedId) {
        if (isDifferentLength(userId, bannedId)) {
            return false;
        }
        for (int index = 0; index < userId.length(); index++) {
            char userIdCharacter = userId.charAt(index);
            char bannedIdCharacter = bannedId.charAt(index);
            if (bannedIdCharacter == '*') {
                continue;
            }
            if (userIdCharacter != bannedIdCharacter) {
                return false;
            }
        }
        return true;
    }

    private void dfs(String[] userId, String[] bannedId, int bitmask, int depth) {
        if (depth == bannedId.length) {
            answer.add(bitmask);
            return;
        }
        String banned = bannedId[depth];
        for (int index = 0; index < userId.length; index++) {
            String user = userId[index];
            if (visited[index]) {
                continue;
            }
            if (isMatched(user, banned)) {
                visited[index] = true;
                dfs(userId, bannedId, bitmask | (1 << index), depth+1);
                visited[index] = false;
            }
        }
    }

    public int solution(String[] user_id, String[] banned_id) {
        visited = new boolean[user_id.length];
        dfs(user_id, banned_id, 0, 0);
        return answer.size();
    }
}
