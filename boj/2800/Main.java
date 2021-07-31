import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class Main {

    private static String solution(String expression) {
        Stack<Integer> stack = new Stack<>();
        List<Element> elements = new ArrayList<>();
        int position = 0;
        for (char c : expression.toCharArray()) {
            if (c == '(') {
                stack.push(position);
                elements.add(new Element(c, true, position++));
                continue;
            }
            if (c == ')') {
                elements.add(new Element(c, true, stack.pop()));
                continue;
            }
            elements.add(new Element(c));
        }

        Deque<String> answer = new LinkedList<>();
        permutation(0, 0, new boolean[position], elements, answer);
        answer.pollFirst();
        return answer.stream().distinct().sorted().collect(Collectors.joining("\n"));
    }

    private static void permutation(int position, int depth, boolean[] selected, List<Element> elements, Deque<String> answer) {
        if (depth == selected.length) {
            StringBuilder result = new StringBuilder();
            for (Element element : elements) {
                if (element.isParenthesis && !selected[element.position]) {
                    continue;
                }
                result.append(element.value);
            }
            answer.add(result.toString());
            return;
        }
        selected[position] = true;
        permutation(position+1, depth+1, selected, elements, answer);
        selected[position] = false;
        permutation(position+1, depth+1, selected, elements, answer);
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String expression = in.readLine();
        in.close();
        System.out.println(solution(expression));
    }
}

class Element {

    char value;
    boolean isParenthesis = false;
    int position = -1;

    Element(char value) {
        this.value = value;
    }

    Element(char value, boolean isParenthesis, int position) {
        this.value = value;
        this.isParenthesis = isParenthesis;
        this.position = position;
    }
}
