import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

class SimpleQueue {

    private Deque<Integer> queue = new LinkedList<>();

    public void push(int x) {
        this.queue.add(x);
    }

    public int pop() {
        if (this.queue.isEmpty()) {
            return -1;
        }
        return this.queue.poll();
    }

    public int size() {
        return this.queue.size();
    }

    public int empty() {
        return this.queue.isEmpty() ? 1 : 0;
    }

    public int front()  {
        if (this.queue.isEmpty()) {
            return -1;
        }
        return this.queue.peek();
    }

    public int back()  {
        if (this.queue.isEmpty()) {
            return -1;
        }
        return this.queue.peekLast();
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        SimpleQueue queue = new SimpleQueue();
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < n; i++) {
            String[] each = in.readLine().split(" ");
            String command = each[0];
            if (command.equals("push")) {
                queue.push(Integer.parseInt(each[1]));
            }
            else if (command.equals("pop")) {
                answer.append(queue.pop()).append("\n");
            }
            else if (command.equals("size")) {
                answer.append(queue.size()).append("\n");
            }
            else if (command.equals("empty")) {
                answer.append(queue.empty()).append("\n");
            }
            else if (command.equals("front")) {
                answer.append(queue.front()).append("\n");
            }
            else {
                answer.append(queue.back()).append("\n");
            }
        }
        in.close();
        System.out.println(answer.toString().stripTrailing());
    }
}
