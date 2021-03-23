import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Line {

    LocalDateTime start;
    LocalDateTime end;
    
    Line(LocalDateTime end, long spendNanoTime) {
        this.end = end;
        this.start = end.minusNanos(spendNanoTime).plusNanos(1*1000*1000);
    }

    @Override
    public String toString() {
        return this.start + " " + this.end;
    }
}

class Solution {

    private long parseSpendTime(String t) {
        Double value = Double.parseDouble(t.substring(0, t.length()-1)) * 1000 * 1000 * 1000;
        return value.longValue();
    }

    private boolean isIn(Line line, LocalDateTime start, LocalDateTime end) {
        return (
            (line.start.isAfter(start) && line.start.isBefore(end))
            || (line.end.isAfter(start) && line.end.isBefore(end))
            || (line.start.isBefore(start) && line.end.isAfter(end))
            || line.start.isEqual(start)
            || line.end.isEqual(start)
        );
    }

    public int solution(String[] lines) {
        List<Line> times = new ArrayList<>();
        for (String each : lines) {
            String[] separated = each.split(" ");
            String dateTime = separated[0].concat(" ").concat(separated[1]);
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
            long spendTime = parseSpendTime(separated[2]);
            times.add(new Line(LocalDateTime.parse(dateTime, format), spendTime));
        }

        Collections.sort(times, (a, b) -> a.end.compareTo(b.end));

        int answer = 0;
        for (int startIndex = 0; startIndex < times.size(); startIndex++) {
            Line each = times.get(startIndex);
            LocalDateTime afterOneSecond = each.end.plusSeconds(1);
            int count = 0;
            int index = 0;
            while (index < times.size()) {
                if (isIn(times.get(index++), each.end, afterOneSecond)) {
                    count++;
                }
            }
            answer = Math.max(answer, count);
        }
        return answer;
    }
}
