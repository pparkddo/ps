class Solution {

    private int getZeroPosition(long number) {
        int position = 63;
        for (int index = 63; index >= 0; index--) {
            if((number & (1L << index)) != 0) {
                continue;
            }
            position = index;
        }
        return position;
    }

    private long getAnswer(long number) {
        int position = getZeroPosition(number);
        if (position == 0) {
            return number + 1;
        }
        long answer = number | (1L << position);
        answer &= ~(1L << (position-1));
        return answer;
    }

    public long[] solution(long[] numbers) {
        long[] answers = new long[numbers.length];
        for (int index = 0; index < numbers.length; index++) {
            long number = numbers[index];
            answers[index] = getAnswer(number);
        }
        return answers;
    }
}