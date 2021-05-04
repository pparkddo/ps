import java.util.Set;

class Solution {

    private static final int STAR = -1;
    private static final int SHARP = -2;
    private static final Set<Integer> leftHandNumber = Set.of(1, 4, 7);
    private static final Set<Integer> rightHandNumber = Set.of(3, 6, 9);
    private static final Set<Integer> centerColumnNumber = Set.of(2, 5, 8, 0);

    private int getRow(int number) {
        if (number <= 0) {
            return 4;
        }
        if (number <= 3) {
            return 1;
        }
        if (number <= 6) {
            return 2;
        }
        return 3;
    }

    private int getDistance(int hand, int number) {
        int distance = Math.abs(getRow(hand)-getRow(number));
        if (centerColumnNumber.contains(hand)) {
            return distance;
        }
        return distance + 1;
    }

    public String solution(int[] numbers, String hand) {
        int left = STAR;
        int right = SHARP;
        StringBuilder answer = new StringBuilder();
        for (int number : numbers) {
            if (leftHandNumber.contains(number)) {
                answer.append("L");
                left = number;
                continue;
            }
            if (rightHandNumber.contains(number)) {
                answer.append("R");
                right = number;
                continue;
            }
            int leftDistance = getDistance(left, number);
            int rightDistance = getDistance(right, number);
            if (leftDistance == rightDistance) {
                if (hand.equals("left")) {
                    answer.append("L");
                    left = number;
                } else {
                    answer.append("R");
                    right = number;
                }
                continue;
            }
            if (leftDistance < rightDistance) {
                answer.append("L");
                left = number;
            } else {
                answer.append("R");
                right = number;
            }
        }
        return answer.toString();
    }
}
