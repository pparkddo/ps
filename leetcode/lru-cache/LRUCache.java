import java.util.LinkedHashMap;

class LRUCache {

    private final LinkedHashMap<Integer, Integer> cache = new LinkedHashMap<>();
    private final int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }
    
    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        Integer value = cache.remove(key);
        cache.put(key, value);
        return value;
    }
    
    public void put(int key, int value) {
        if (cache.size() == capacity && !cache.containsKey(key)) {
            Integer lastKey = cache.keySet().toArray(Integer[]::new)[0];
            cache.remove(lastKey);
        }
        if (cache.containsKey(key)) {
            cache.remove(key);
        }
        cache.put(key, value);
    }
}
