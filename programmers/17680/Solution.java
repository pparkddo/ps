import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        Queue<String> cache = new LinkedList<>();
        for (String city : cities) {
            city = city.toLowerCase();
            if (cache.contains(city)) {
                cache.remove(city);
                cache.add(city);
                answer += 1;
                continue;
            }
            if (cache.size() >= cacheSize) {
                cache.poll();
            }
            if (cacheSize != 0) {
                cache.add(city);
            }
            answer += 5;
        }
        return answer;
    }
}
