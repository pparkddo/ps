import java.util.ArrayList;
import java.util.List;
import java.util.Set;

class Solution {

    Set<String> operators = Set.of("+", "-", "*");

    private boolean isOperator(char c) {
        return isOperator(String.valueOf(c));
    }
    
    private boolean isOperator(String s) {
        return operators.contains(s);
    }

    private List<String> getElements(String expression) {
        List<String> elements = new ArrayList<>();
        int index = 0;
        int startIndex = 0;
        while (index < expression.length()) {
            char currentCharacter = expression.charAt(index);
            if (isOperator(currentCharacter)) {
                elements.add(expression.substring(startIndex, index));
                elements.add(String.valueOf(currentCharacter));
                startIndex = index + 1;
            }
            index++;
        }
        elements.add(expression.substring(startIndex));
        return elements;
    }

    private List<List<String>> getPriorities() {
        List<List<String>> priorities = new ArrayList<>();
        priorities.add(List.of("+", "-", "*"));
        priorities.add(List.of("+", "*", "-"));
        priorities.add(List.of("-", "+", "*"));
        priorities.add(List.of("-", "*", "-"));
        priorities.add(List.of("*", "+", "-"));
        priorities.add(List.of("*", "-", "+"));
        return priorities;
    }

    private long calculate(long a, String operator, long b) {
        switch (operator) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
        }
        throw new IllegalArgumentException("Wrong operator : " + operator);
    }

    private long calculateByPriority(List<String> elements, List<String> priority) {
        for (String p : priority) {
            int index = 0;
            while (index < elements.size()) {
                String element = elements.get(index);
                if (isOperator(element) && element.equals(p)) {
                    long previous = Long.parseLong(elements.get(index-1));
                    long next = Long.parseLong(elements.get(index+1));
                    long value = calculate(previous, element, next);
                    elements.add(index+2, String.valueOf(value));
                    elements.remove(index-1);
                    elements.remove(index-1);
                    elements.remove(index-1);
                    index = 0;
                }
                index++;
            }
        }
        return Math.abs(Long.parseLong(elements.get(0)));
    }

    public long solution(String expression) {
        List<String> elements = getElements(expression);
        System.out.println(elements);
        List<List<String>> priorities = getPriorities();
        long answer = 0;
        for (List<String> priority : priorities) {
            long value = calculateByPriority(new ArrayList<>(elements), priority);
            answer = Math.max(answer, value);
        }
        return answer;
    }
}