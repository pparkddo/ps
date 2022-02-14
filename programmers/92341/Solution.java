import java.time.Duration;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

class Solution {

    public int[] solution(int[] fees, String[] records) {
        Map<String, LocalTime> lastRecord = new HashMap<>();
        Map<String, Long> minutes = new HashMap<>();

        for (String record : records) {
            String[] separated = record.split(" ");
            LocalTime time = toLocalTime(separated[0]);
            String id = separated[1];
            String inOut = separated[2];

            if ("OUT".equals(inOut)) {
                long diff = getDiffMinutes(lastRecord.remove(id), time);
                minutes.put(id, minutes.getOrDefault(id, 0L) + diff);
            } else {
                lastRecord.put(id, time);
            }
        }

        for (String id : lastRecord.keySet()) {
            LocalTime time = LocalTime.MAX;
            long diff = getDiffMinutes(lastRecord.get(id), time);
            minutes.put(id, minutes.getOrDefault(id, 0L) + diff);
        }

        int defaultMinute = fees[0];
        int defaultFare = fees[1];
        int unitMinute = fees[2];
        int unitFare = fees[3];

        return minutes.keySet().stream()
            .sorted()
            .mapToInt(id -> getFare(defaultMinute, defaultFare, unitMinute, unitFare, minutes.get(id)))
            .toArray();
    }

    private int getFare(int defaultMinute, int defaultFare, int unitMinute, int unitFare,
        long minutes) {

        if (minutes <= defaultMinute) {
            return defaultFare;
        }

        int additional = (int) Math.ceil((double) (minutes-defaultMinute) / unitMinute);
        return defaultFare + additional * unitFare;
    }

    private long getDiffMinutes(LocalTime start, LocalTime end) {
        return Duration.between(start, end).toMinutes();
    }

    private LocalTime toLocalTime(String timeString) {
        String[] separated = timeString.split(":");
        return LocalTime.of(Integer.parseInt(separated[0]), Integer.parseInt(separated[1]));
    }
}