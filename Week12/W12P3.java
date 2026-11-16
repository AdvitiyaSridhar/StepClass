package Week12;

import java.util.HashMap;

public class W12P3 {

    static class Node {
        int key;
        String page;
        Node prev, next;

        Node(int key, String page) {
            this.key = key;
            this.page = page;
        }
    }

    static class RecentCache {
        int capacity;
        HashMap<Integer, Node> map;
        Node head, tail;

        RecentCache(int capacity) {
            this.capacity = capacity;
            map = new HashMap<>();

            head = new Node(0, "");
            tail = new Node(0, "");

            head.next = tail;
            tail.prev = head;
        }

        // Remove a node from the list
        void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        // Insert node immediately after head
        void insertFront(Node node) {
            node.next = head.next;
            node.prev = head;

            head.next.prev = node;
            head.next = node;
        }

        String get(int key) {
            if (!map.containsKey(key))
                return "-1";

            Node node = map.get(key);

            remove(node);
            insertFront(node);

            return node.page;
        }

        void put(int key, String page) {
            if (map.containsKey(key)) {
                Node node = map.get(key);
                node.page = page;

                remove(node);
                insertFront(node);

                System.out.println("put(" + key + "," + page + ") updated");
                return;
            }

            String message = "put(" + key + "," + page + ")";

            if (map.size() == capacity) {
                Node lru = tail.prev;

                remove(lru);
                map.remove(lru.key);

                message += " evicted " + lru.key;
            }

            Node node = new Node(key, page);
            map.put(key, node);
            insertFront(node);

            System.out.println(message);
        }

        void display() {
            System.out.print("Cache (most recent first): ");

            Node current = head.next;

            while (current != tail) {
                System.out.print(current.key + "=" + current.page);

                current = current.next;

                if (current != tail)
                    System.out.print(", ");
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {
        RecentCache cache = new RecentCache(2);

        cache.put(1, "Home");
        cache.put(2, "Notes");

        System.out.println("get(1) -> " + cache.get(1));

        cache.put(3, "Quiz");

        System.out.println("get(2) -> " + cache.get(2));

        cache.put(1, "Home2");
        cache.put(4, "Mail");

        System.out.println("get(3) -> " + cache.get(3));
        System.out.println("get(1) -> " + cache.get(1));

        cache.display();
    }
}
