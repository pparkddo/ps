class Solution {

    private final int[] state = new int[11];
    private int[] info;
    private int[] answer;
    private int differenceBetweenScores;

    public int[] solution(int n, int[] info) {
        this.info = info;
        dfs(0, n, 0);

        if (answer == null) {
            return new int[]{-1};
        }

        return answer;
    }

    private void dfs(int idx, int remains, int scoreOfLion) {
        if (remains == 0) {
            int scoreOfApeach = calculateScoreOfApeach();
            if (scoreOfLion > scoreOfApeach
                && (scoreOfLion - scoreOfApeach) >= differenceBetweenScores) {
                if (scoreOfLion - scoreOfApeach == differenceBetweenScores && !hasMoreLowScore()) {
                    return;
                }
                answer = state.clone();
                differenceBetweenScores = scoreOfLion - scoreOfApeach;
            }
            return;
        }

        for (int i = idx; i < info.length; i++) {
            int currentApeachNumber = info[i];
            int currentScore = 10 - i;
            if (remains > 0 && remains > currentApeachNumber) {
                int numberNeededToWin = currentApeachNumber + 1;
                state[i] = numberNeededToWin;
                dfs(i + 1, remains - numberNeededToWin, scoreOfLion + currentScore);
                state[i] = 0;
            } else if (i == info.length - 1) {
                state[i] = remains;
                dfs(i + 1, 0, scoreOfLion);
                state[i] = 0;
            }
        }
    }

    private int calculateScoreOfApeach() {
        int result = 0;
        for (int i = 0; i < info.length; i++) {
            if (state[i] == 0 && info[i] != 0) {
                result += (10 - i);
            }
        }
        return result;
    }

    private boolean hasMoreLowScore() {
        if (answer == null) {
            return true;
        }

        for (int i = info.length - 1; i >= 0; i--) {
            if (answer[i] == state[i]) {
                continue;
            }
            return answer[i] < state[i];
        }
        return true;
    }
}
