class Solution {
    
    private int getYellow(int row, int column) {
       return (row-2) * Math.max(column-2, 0);
    }
    
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        int totalCount = brown + yellow;
        for (int i = 1; (i*i) <= totalCount; i++) {
            if (totalCount % i != 0) {
                continue;
            }
            if (yellow == getYellow(totalCount/i, i)) {
                answer[0] = totalCount / i;
                answer[1] = i;
                break;
            }
        }
        return answer;
    }
}
