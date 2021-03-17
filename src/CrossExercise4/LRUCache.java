package CrossExercise4;

import java.util.*;

/**
 * Implement a least recently used cache. It should provide set(), get() operations.
 * If not exists, return null (Java), false (C++), -1(Python).
 */
public class LRUCache<K, V> {
    static class Node<K, V> {
        Node<K, V> pre;
        Node<K, V> next;
        K key;
        V value;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        void update(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int limit;
    private Node<K, V> head;
    private Node<K, V> tail;
    private Map<K, Node<K, V>> map;

    // limit is the max capacity of the cache
    public LRUCache(int limit) {
        this.limit = limit;
        this.map = new HashMap<K, Node<K, V>>();
    }

    public void set(K key, V value) {
        Node<K, V> node = null;
        if (map.containsKey(key)) {
            node = map.get(key);
            node.value = value;
            remove(node);
        } else if (map.size() < limit) {
            node = new Node<K, V>(key, value);
        } else {
            node = tail;
            remove(node);
            node.update(key, value);
        }
        append(node);
    }

    public V get(K key) {
        Node<K, V> node = map.get(key);
        if (node == null) {
            return null;
        }
        remove(node);
        append(node);
        return node.value;
    }

    private Node<K, V> remove(Node<K, V> node) {
        map.remove(node.key);
        if (node.pre != null) {
            node.pre.next = node.next;
        }
        if (node.next != null) {
            node.next.pre = node.pre;
        }
        if (node == head) {
            head = head.next;
        }
        if (node == tail) {
            tail = tail.pre;
        }
        node.next = node.pre = null;
        return node;
    }

    private Node<K, V> append(Node<K, V> node) {
        map.put(node.key, node);
        if (head == null) {
            head = tail = node;
        } else {
            node.next = head;
            head.pre = node;
            head = node;
        }
        return node;
    }
}
