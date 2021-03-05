import java.util.Stack;

class Element {
    
    int value;
    int min;
    
    Element(int value, int min) {
        this.value = value;
        this.min = min;
    }
}

class MinStack {
    
    Stack<Element> stack;
    
    /** initialize your data structure here. */
    public MinStack() {
        stack = new Stack<>();
    }
    
    public void push(int x) {
        int min = stack.isEmpty() ? x : Math.min(x, this.getMin());
        stack.push(new Element(x, min));
    }
    
    public void pop() {
        stack.pop();
    }
    
    public int top() {
        return stack.peek().value;
    }
    
    public int getMin() {
        return stack.peek().min;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */