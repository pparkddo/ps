import java.util.HashMap;
import java.util.Map;

class LinkedListLRUCache {

    private final Map<Integer, Node> cache = new HashMap<>();
    private Node head = new Node();
    private Node tail = new Node();
    private final int capacity;
    
    public LinkedListLRUCache(int capacity) {
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        Node node = cache.get(key);
        remove(node);
        add(node);
        return node.value;
    }
    
    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            remove(cache.get(key));
        }
        if (cache.size() == capacity) {
            remove(tail.prev);
        }
        add(new Node(key, value));
    }

    private void remove(Node node) {
        cache.remove(node.key);
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void add(Node node) {
        cache.put(node.key, node);
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }
}

class Node {

    Node prev;
    Node next;
    int key = 0;
    int value = 0;

    Node() {}

    Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}