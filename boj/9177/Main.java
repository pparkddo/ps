import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    private static boolean solution(String[] words) {
        return bfs(words);
    }

    private static boolean bfs(String[] words) {
        String firstWord = words[0];
        String secondWord = words[1];
        String word = words[2];

        boolean[][] visited = new boolean[firstWord.length()+1][secondWord.length()+1];
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0, 0));
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int firstIndex = node.firstIndex;
            int secondIndex = node.secondIndex;
            int wordIndex = firstIndex + secondIndex;
            if (wordIndex == word.length()) {
                return true;
            }
            char c = word.charAt(wordIndex);
            if (firstIndex < firstWord.length() && c == firstWord.charAt(firstIndex) && !visited[firstIndex+1][secondIndex]) {
                queue.add(new Node(firstIndex+1, secondIndex));
                visited[firstIndex+1][secondIndex] = true;
            }
            if (secondIndex < secondWord.length() && c == secondWord.charAt(secondIndex) && !visited[firstIndex][secondIndex+1]) {
                queue.add(new Node(firstIndex, secondIndex+1));
                visited[firstIndex][secondIndex+1] = true;
            }
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        StringBuilder answers = new StringBuilder();
        for (int number = 1; number <= n; number++) {
            boolean result = solution(in.readLine().split(" "));
            StringBuilder answer = new StringBuilder("Data set ")
                .append(number)
                .append(": ")
                .append(result ? "yes" : "no");
            answers.append(answer).append("\n");
        }
        in.close();
        System.out.println(answers.toString().trim());
    }
}

class Node {

    int firstIndex;
    int secondIndex;

    Node(int firstIndex, int secondIndex) {
        this.firstIndex = firstIndex;
        this.secondIndex = secondIndex;
    }
}