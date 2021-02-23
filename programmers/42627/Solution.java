import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Job implements Comparable<Job> {
    
    int request;
    int spend;
    
    Job(int request, int spend) {
        this.request = request;
        this.spend = spend;
    }
    
    @Override
    public int compareTo(Job job) {
        return Integer.compare(this.spend, job.spend);
    }
}

class Solution {
    public int solution(int[][] jobs) {
        Arrays.sort(jobs, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return Integer.compare(a[0], b[0]);
            }
        });
        PriorityQueue<Job> jobQueue = new PriorityQueue<>();
        int time = 0;
        int index = 0;
        int answer = 0;
        while (true) {
            if (index == jobs.length && jobQueue.isEmpty()) {
                break;
            }
            while (index < jobs.length && time >= jobs[index][0]) {
                jobQueue.add(new Job(jobs[index][0], jobs[index][1]));
                index++;
            }
            if (index < jobs.length && jobQueue.isEmpty()) {
                time = jobs[index][0];
            }
            if (!jobQueue.isEmpty()) {
                Job job = jobQueue.poll();
                answer += time - job.request + job.spend;
                time += job.spend;
            } 
        }
        return answer / jobs.length;
    }
}

class Main {

    public static void main(String[] args) {
        System.out.println(
            new Solution().solution(
                new int[][] {
                    {0, 3},
                    {1, 9},
                    {2, 6}
                }
            )
        );
        System.out.println(
            new Solution().solution(
                new int[][] {
                    {36, 3},
                    {1, 9},
                    {2, 6}
                }
            )
        );
        System.out.println(
            new Solution().solution(
                new int[][] {
                    {0, 20},
                    {3, 4},
                    {3, 5},
                    {17, 2},
                }
            )
        );
    }
}
