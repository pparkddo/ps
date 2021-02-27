import java.util.LinkedList;
import java.util.Queue;

class Node {

    String word;
    int depth;

    Node(String word, int depth) {
        this.word = word;
        this.depth = depth;
    }
}

class Solution {

    String begin;
    String target;
    String[] words;

    private boolean isOneCharChanged(String a, String b) {
        int count = 0;
        for (int index = 0; index < a.length(); index++) {
            if (a.charAt(index) == b.charAt(index)) {
                continue;
            }
            count++;
        }
        return count == 1;
    }

    private int bfs() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(begin, 0));
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            String current = node.word;
            int depth = node.depth;
            if (current.equals(target)) {
                return depth;
            }
            for (String word : words) {
                if (isOneCharChanged(current, word)) {
                    queue.add(new Node(word, depth+1));
                }
            }
        }
        return 0;
    }

    public int solution(String begin, String target, String[] words) {
        this.begin = begin;
        this.target = target;
        this.words = words;

        boolean isPossible = false;
        for (String word : words) {
            if (word.equals(target)) {
                isPossible = true;
            }
        }
        if (!isPossible) {
            return 0;
        }
        return bfs();
    }
}
