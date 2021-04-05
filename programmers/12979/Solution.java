class Solution {

    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        int stationIndex = 0;
        int location = 1;
        while (location <= n) {
            if (stationIndex < stations.length && location >= stations[stationIndex]-w) {
                location = stations[stationIndex] + w + 1;
                stationIndex++;
                continue;
            }
            location += 2 * w + 1;
            answer++;
        }
        return answer;
    }
}
