import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        long[][] timestamps = new long[n][2];
        for (int i = 0; i < n; i++) {
            String[] each = in.readLine().split(" ");
            timestamps[i][0] = Long.parseLong(each[0]);
            timestamps[i][1] = Long.parseLong(each[1]);
        }
        in.close();

        Arrays.sort(timestamps, new Comparator<long[]>(){
			@Override
			public int compare(long[] o1, long[] o2) {
                if (o1[1] == o2[1]) {
				    return Long.compare(o1[0], o2[0]);
                }
                return Long.compare(o1[1], o2[1]);
			}
        });

        long lastTimestamp = Long.MIN_VALUE;
        int answer = 0;
        for (int i = 0; i < timestamps.length; i++) {
            long startTimestamp = timestamps[i][0];
            long endTimestamp = timestamps[i][1];
            if (lastTimestamp > startTimestamp) {
                continue;
            }
            lastTimestamp = endTimestamp;
            answer += 1;
        }
        System.out.println(answer);
    }
}
