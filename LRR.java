import java.util.HashMap;
import java.util.Map;

class Node {
    int key;
    int value;
    Node prev;
    Node next;
    
    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

class LRUCache {
    private Map<Integer, Node> cache; // Maps keys to nodes
    private Node head;                // Dummy head node (before least recently used)
    private Node tail;                // Dummy tail node (after most recently used)
    private int capacity;             // Maximum number of items in the cache
    
    // Constructor to initialize the cache
    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>();
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }
    
    // Retrieves the value for a key
    public int get(int key) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            removeNode(node);  // Remove from current position
            addNode(node);     // Move to tail (most recently used)
            return node.value;
        }
        return -1;  // Key not found
    }
    
    // Inserts or updates a key-value pair
    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            // Update existing key
            Node node = cache.get(key);
            node.value = value;
            removeNode(node);
            addNode(node);
        } else {
            // Add new key
            if (cache.size() == capacity) {
                // Remove least recently used item
                Node lru = head.next;
                removeNode(lru);
                cache.remove(lru.key);
            }
            Node newNode = new Node(key, value);
            addNode(newNode);
            cache.put(key, newNode);
        }
    }
    
    // Adds a node right before the tail (most recently used position)
    private void addNode(Node node) {
        node.next = tail;
        node.prev = tail.prev;
        tail.prev.next = node;
        tail.prev = node;
    }
    
    // Removes a node from the list
    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    
    // Main method for testing
    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2); // Capacity of 2
        
        cache.put(1, 1);                  // Cache is {1=1}
        cache.put(2, 2);                  // Cache is {1=1, 2=2}
        System.out.println(cache.get(1)); // Returns 1, cache is {2=2, 1=1}
        cache.put(3, 3);                  // Evicts key 2, cache is {1=1, 3=3}
        System.out.println(cache.get(2)); // Returns -1 (not found)
        cache.put(4, 4);                  // Evicts key 1, cache is {3=3, 4=4}
        System.out.println(cache.get(1)); // Returns -1 (not found)
        System.out.println(cache.get(3)); // Returns 3
        System.out.println(cache.get(4)); // Returns 4
    }
}