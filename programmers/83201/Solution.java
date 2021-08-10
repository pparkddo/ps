import java.util.Arrays;
import java.util.TreeMap;

class Solution {

    public String solution(int[][] scores) {
        StringBuilder answer = new StringBuilder();
        for (int index = 0; index < scores[0].length; index++) {
            answer.append(getGrade(getAverageScore(scores, index)));
        }
        return answer.toString();
    }

    private TreeMap<Integer, Integer> getScoreCount(int[][] scores, int index) {
        TreeMap<Integer, Integer> count = new TreeMap<>();
        Arrays.stream(scores)
            .mapToInt(score -> score[index])
            .forEach(v -> count.put(v, count.getOrDefault(v, 0)+1));
        return count;
    }

    private double getAverageScore(int[][] scores, int index) {
        int selfScore = scores[index][index];
        TreeMap<Integer, Integer> scoreCount = getScoreCount(scores, index);
        int sum = getSum(scoreCount);
        int count = scores.length;
        if (shouldExclude(scoreCount, selfScore)) {
            sum -= selfScore;
            count -= 1;
        }
        return (double) sum / count;
    }

    private int getSum(TreeMap<Integer, Integer> scoreCount) {
        return scoreCount.entrySet().stream()
            .mapToInt(each -> each.getKey() * each.getValue())
            .sum();
    }

    private boolean shouldExclude(TreeMap<Integer, Integer> scoreCount, int selfScore) {
        if (selfScore == scoreCount.firstKey() && scoreCount.firstEntry().getValue() == 1) {
            return true;
        }
        return selfScore == scoreCount.lastKey() && scoreCount.lastEntry().getValue() == 1;
    }

    private String getGrade(double score) {
        if (score >= 90) {
            return "A";
        }
        if (score >= 80) {
            return "B";
        }
        if (score >= 70) {
            return "C";
        }
        if (score >= 50) {
            return "D";
        }
        return "F";
    }
}
