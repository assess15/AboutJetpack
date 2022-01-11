package com.demo.lib_network.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * 双链表 + 哈希
 */
public class LruCache2 {

    class Node {
        int key;
        int value;
        Node prev;
        Node next;

        public Node() {
        }

        public Node(int _key, int _value) {
            key = _key;
            value = _value;
        }
    }

    private int size;
    private int capacity;
    private Node head, tail;
    private final Map<Integer, Node> cache = new HashMap<>();

    public LruCache2(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        // 伪节点
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) {
            return -1;
        }
        gotoHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = cache.get(key);
        if (node == null) {
            Node nNode = new Node(key, value);
            cache.put(key, nNode);
            addToHead(nNode);
            ++size;
            if (size > capacity) {
                Node tail = removeTail();
                cache.remove(tail.key);
                --size;
            }
        } else {
            node.value = value;
            gotoHead(node);
        }
    }

    private void addToHead(Node node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void gotoHead(Node node) {
        removeNode(node);
        addToHead(node);
    }

    private Node removeTail() {
        Node n = tail.prev;
        removeNode(n);
        return n;
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
}
