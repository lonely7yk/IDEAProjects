/*
Design and implement a data structure for Least Recently Used (LRU)
 cache. It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key
if the key exists in the cache, otherwise return -1.

put(key, value) - Set or insert the value if the key is not
already present. When the cache reached its capacity, it should
invalidate the least recently used item before inserting a new
item.

The cache is initialized with a positive capacity.

Follow up:
Could you do both operations in O(1) time complexity?

Example:

LRUCache cache = new LRUCache( 2 );

cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // returns 1
cache.put(3, 3);    // evicts key 2
cache.get(2);       // returns -1 (not found)
cache.put(4, 4);    // evicts key 1
cache.get(1);       // returns -1 (not found)
cache.get(3);       // returns 3
cache.get(4);       // returns 4
 */


import java.util.HashMap;

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

//// 13.7% wait improve
//public class LeetCode146LRUCache {
//    HashMap<Integer, Integer> map;
//    List<Integer> list;
//    int capacity;
//
//    public LeetCode146LRUCache(int capacity) {
//        map = new HashMap<>();
//        list = new ArrayList<>();
//        this.capacity = capacity;
//    }
//
//    public int get(int key) {
//        if (map.containsKey(key)) {
//            resortList(list, key);
//            return map.get(key);
//        }
//        return -1;
//    }
//
//    public void put(int key, int value) {
//        if (map.containsKey(key)) {
//            map.put(key, value);
//            resortList(list, key);
//            return;
//        }
//
//        if (map.size() == capacity) {
//            int leastUseKey = list.get(0);
//            list.remove(0);
//            map.remove(leastUseKey);
//        }
//
//        map.put(key, value);
//        list.add(key);
//    }
//
//    // 重新排列：从旧到新
//    private void resortList(List<Integer> list, int key) {
//        for (int i = 0; i < list.size(); i++) {
//            // 找到该 key 把它删掉，然后在末尾添加它
//            if (list.get(i) == key) {
//                list.remove(i);
//                list.add(key);
//                break;
//            }
//        }
//    }
//}

// time O(1)  double linked list   56.57%
public class LeetCode146LRUCache {
    class ListNode {
        int key;
        int val;
        ListNode pre;
        ListNode next;

        public ListNode(int key, int val) {
            this.key = key;
            this.val = val;
            this.pre = null;
            this.next = null;
        }
    }

    class DoubleLinkedList {
        private ListNode head;
        private ListNode tail;

        public DoubleLinkedList() {
            head = new ListNode(0, 0);
            tail = new ListNode(0, 0);
            head.next = tail;
            tail.pre = head;
        }

        private void addFirst(ListNode first) {
            head.next.pre = first;
            first.next = head.next;
            head.next = first;
            first.pre = head;
        }

        private void move2First(ListNode node) {
            node.pre.next = node.next;
            node.next.pre = node.pre;
            addFirst(node);
        }

        private ListNode removeLast() {
            ListNode last = tail.pre;
            last.pre.next = tail;
            tail.pre = last.pre;
            return last;
        }
    }

    private HashMap<Integer, ListNode> map;
    private int capacity;
    private DoubleLinkedList list;

    public LeetCode146LRUCache(int capacity) {
        this.map = new HashMap<>();
        this.capacity = capacity;
        this.list = new DoubleLinkedList();
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        } else {
            list.move2First(map.get(key));
            return map.get(key).val;
        }
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            ListNode node = map.get(key);
            node.val = value;
            list.move2First(node);
            return;
        }

        ListNode node = new ListNode(key, value);
        map.put(key, node);
        list.addFirst(node);

        if (map.size() > capacity) {
            ListNode last = list.removeLast();
            map.remove(last.key);
        }
    }
}