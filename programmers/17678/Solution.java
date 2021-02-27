import java.time.LocalTime;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

class Solution {
    
    private int getHour(String s) {
        return Integer.parseInt(s.substring(0, 2));
    }

    private int getMinute(String s) {
        return Integer.parseInt(s.substring(3));
    }

    public String solution(int n, int t, int m, String[] timetable) {
        PriorityQueue<LocalTime> queue = new PriorityQueue<>();
        for (String s: timetable) {
            if (s.equals("24:00")) {
                queue.add(LocalTime.of(23, 59));
                continue;
            }
            queue.add(LocalTime.of(getHour(s), getMinute(s)));
        }

        LocalTime busTime = LocalTime.of(9, 0).minusMinutes(t);
        LocalTime lastCrewTime = null;
        int lastBusSize = 0;
        for (int round = 0; round < n; round++) {
            busTime = busTime.plusMinutes(t);
            Queue<LocalTime> bus = new LinkedList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                if (bus.size() >= m) {
                    continue;
                }
                if (busTime.compareTo(queue.peek()) >= 0) {
                    lastCrewTime = queue.poll();
                    bus.add(lastCrewTime);
                }
            }
            lastBusSize = bus.size();
        }

        if (lastBusSize < m) {
            return busTime.toString();
        }
        return lastCrewTime.minusMinutes(1).toString();
    }
}