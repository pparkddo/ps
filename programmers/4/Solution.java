import java.time.LocalTime;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

class Solution {
    
    int n;
    int t;
    int m;
    String[] timetable;

    private int getHour(String s) {
        return Integer.parseInt(s.substring(0, 2));
    }

    private int getMinute(String s) {
        return Integer.parseInt(s.substring(3));
    }

    private boolean isPossible(LocalTime rideTime) {
        PriorityQueue<LocalTime> queue = new PriorityQueue<>();
        for (String s: timetable) {
            if (s.equals("24:00")) {
                queue.add(LocalTime.of(23, 59));
            }
            queue.add(LocalTime.of(getHour(s), getMinute(s)));
        }
        queue.add(rideTime);
        LocalTime time = LocalTime.of(9, 0).minusMinutes(t);
        for (int round = 0; round < n; round++) {
            time = time.plusMinutes(t);
            Queue<LocalTime> bus = new LinkedList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                if (bus.size() >= m) {
                    continue;
                }
                if (time.compareTo(queue.peek()) >= 0) {
                    bus.add(queue.poll());
                }
            }
        }
        if (queue.isEmpty()) {
            return time.compareTo(rideTime) >= 0;
        }
        return time.compareTo(rideTime) >= 0 && rideTime.compareTo(queue.peek()) < 0;
    }

    public String solution(int n, int t, int m, String[] timetable) {
        this.n = n;
        this.t = t;
        this.m = m;
        this.timetable = timetable;
       
        LocalTime rideTime = LocalTime.of(23, 59);
        while (rideTime.compareTo(LocalTime.MIN) == 1) {
            if (isPossible(rideTime)) {
                break;
            };
            rideTime = rideTime.minusMinutes(1);
        }

        return rideTime.toString();
    }
}
