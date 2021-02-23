import java.util.Queue;
import java.util.LinkedList;

class Document {
    
    int id;
    int priority;
    
    Document(int id, int priority) {
        this.id = id;
        this.priority= priority;
    }
    
    @Override
    public boolean equals(Object obj) {
        Document document = (Document) obj;
        return (
            this.id == document.id 
            && this.priority == document.priority
        );
    }
    
    @Override
    public String toString() {
        return this.id + " " + this.priority;
    }
}

class Solution {
    
    private boolean hasHigherPriority(Queue<Document> queue) {
        Document document = queue.peek();
        for (Document each : queue) {
            if (each.priority > document.priority) {
                return true;
            }
        }
        return false;
    }
        
    public int solution(int[] priorities, int location) {
        Queue<Document> queue = new LinkedList<>();
        Document target = null;
        for (int i = 0; i < priorities.length; i++) {
            Document document = new Document(i, priorities[i]);
            if (i == location) {
                target = document;
            }
            queue.add(document);
        }
        int answer = 0;
        while (!queue.isEmpty()) {
            if (this.hasHigherPriority(queue)) {
                queue.add(queue.poll());
                continue;
            }
            Document document = queue.poll();
            answer++;
            if (document.equals(target)) {
                break;
            }
        }
        return answer;
    }
}