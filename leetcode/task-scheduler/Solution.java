import java.util.HashMap;
import java.util.Map;

class Solution {

    private static final char IDLE = '-';

    private Map<Character, Integer> getCounts(char[] tasks) {
        Map<Character, Integer> counts = new HashMap<>();
        for (char task : tasks) {
            counts.put(task, counts.getOrDefault(task, 0)+1);
        }
        return counts;
    }

    private Map<Character, Integer> getTimes(Map<Character, Integer> counts) {
        Map<Character, Integer> times = new HashMap<>();
        for (char each : counts.keySet()) {
            times.put(each, 0);
        }
        return times;
    }

    private char getAvailableTask(Map<Character, Integer> counts, Map<Character, Integer> times) {
        char task = IDLE;
        int maxCount = Integer.MIN_VALUE;
        
        for (char key : counts.keySet()) {
            if (times.get(key) != 0) {
                continue; 
            }
            int count = counts.get(key);
            if (count == 0) {
                continue;
            }
            if (count > maxCount) {
                task = key;
                maxCount = count;
            }
        }

        return task;
    }

    private void cool(Map<Character, Integer> times) {
        for (char key : times.keySet()) {
            int time = times.get(key);
            if (time > 0) {
                times.put(key, time-1);
            }
        }
    }

    public int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> counts = getCounts(tasks);
        Map<Character, Integer> times = getTimes(counts);

        int answer = 0;
        int doneCount = 0;
        while (true) {
            if (doneCount == tasks.length) {
                break;
            }
            answer++;
            char task = getAvailableTask(counts, times);
            cool(times);
            if (task == IDLE) {
                continue;
            }
            times.put(task, n);
            counts.put(task, counts.get(task)-1);
            doneCount++;
        }

        return answer;
    }
}
