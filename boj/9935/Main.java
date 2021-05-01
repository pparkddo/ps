import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.stream.Collectors;

public class Main {

    private static boolean canExplode(Stack<Character> stack, String explosion) {
        if (stack.size() < explosion.length()) {
            return false;
        }
        for (int index = 0; index < explosion.length(); index++) {
            char e = explosion.charAt(index);
            char s = stack.get(stack.size()-explosion.length()+index);
            if (e != s) {
                return false;
            }
        }
        return true;
    }

    private static void explode(Stack<Character> stack, String explosion) {
        for (int index = 0; index < explosion.length(); index++) {
            stack.pop();
        }
    }

    private static String solution(String sentence, String explosion) {
        Stack<Character> stack = new Stack<>();
        for (char each : sentence.toCharArray()) {
            stack.push(each);
            if (each == explosion.charAt(explosion.length()-1) && canExplode(stack, explosion)) {
                explode(stack, explosion);
            }
        }
        return stack.isEmpty() ? "FRULA" : stack.stream().map(String::valueOf).collect(Collectors.joining());
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String sentence = in.readLine();
        String explosion= in.readLine();
        System.out.println(solution(sentence, explosion));
        in.close();
    }
}
