import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

class Solution {
    
    boolean isFinished = false;
    Stack<String> stack = new Stack<>();
    List<String> answer = new ArrayList<>();
    String[][] tickets;
    boolean[] visited;
    
    private void dfs(String departure, String destination, int depth) {
        if (isFinished) {
            return;
        }
        stack.add(destination);
        if (depth == tickets.length) {
            isFinished = true;
            answer = new ArrayList<>(stack);
            return;
        }
        for (int i = 0; i < tickets.length; i++) {
            String dep = tickets[i][0];
            String des = tickets[i][1];
            if (!destination.equals(dep) || visited[i]) {
                continue;
            }
            visited[i] = true;
            dfs(dep, des, depth+1);
            visited[i] = false;
            if (!stack.isEmpty()) {
                stack.pop();
            }
        }
    }
    
    public String[] solution(String[][] tickets) {
        this.tickets = tickets;
        this.visited = new boolean[this.tickets.length];
        Arrays.sort(tickets, (a, b) -> {
        	if (a[0].equals(b[0])) {
                return a[1].compareTo(b[1]);
            }
            return a[0].compareTo(b[0]);
        });
        dfs("", "ICN", 0);
        return answer.stream().toArray(String[]::new);
    }
}

class Main {

    public static void main(String[] args) {
        System.out.println(
            Arrays.toString(
                new Solution().solution(
                    new String[][] {
                        {"ICN", "JFK"},
                        {"HND", "IAD"},
                        {"JFK", "HND"},
                    }
                )
            )
        );
        System.out.println(
            Arrays.toString(
                new Solution().solution(
                    new String[][] {
                        {"ICN", "A"},
                        {"ICN", "A"},
                        {"A", "ICN"},
                        {"A", "C"},
                    }
                )
            )
        );  // Test case 1
        System.out.println(
            Arrays.toString(
                new Solution().solution(
                    new String[][] {
                        {"ICN", "B"},
                        {"ICN", "C"},
                        {"C", "D"},
                        {"D", "ICN"},
                    }
                )
            )
        );  // Test case 2
    }
}