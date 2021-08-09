class Solution {

    public String solution(String play_time, String adv_time, String[] logs) {
        int playSecond = timeToSecond(play_time);
        long[] times = new long[playSecond+1];
        for (String log : logs) {
            String[] separated = log.split("-");
            int startLogSecond = timeToSecond(separated[0]);
            int endLogSecond = timeToSecond(separated[1]);
            times[startLogSecond]++;
            times[endLogSecond]--;
        }

        for (int i = 1; i <= playSecond; i++) {
            times[i] += times[i-1];
        }
        
        for (int i = 1; i <= playSecond; i++) {
            times[i] += times[i-1];
        }

        int advertisingSecond = timeToSecond(adv_time);
        long maxCount = times[advertisingSecond-1];
        int answer = 0;
        for (int time = 0; time + advertisingSecond <= playSecond; time++) {
            long count = times[time+advertisingSecond] - times[time];
            if (count > maxCount) {
                maxCount = count;
                answer = time + 1;
            }
        }
        return secondToTime(answer);
    }

    private String secondToTime(int second) {
        return new StringBuilder()
            .append(String.format("%02d", second / 3600))
            .append(":")
            .append(String.format("%02d", second / 60 % 60))
            .append(":")
            .append(String.format("%02d", second % 60))
            .toString();
    }

    private int timeToSecond(String time) {
        String[] separated = time.split(":");
        int hour = Integer.parseInt(separated[0]);
        int minute = Integer.parseInt(separated[1]);
        int second = Integer.parseInt(separated[2]);
        return timeToSecond(hour, minute, second);
    }

    private int timeToSecond(int hour, int minute, int second) {
        return hour * 3600 + minute * 60 + second;
    }
}