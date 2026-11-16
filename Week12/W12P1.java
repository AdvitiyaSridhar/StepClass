package Week12;

public class W12P1 {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            next = null;
        }
    }

    static String detectLoop(Node head) {
        Node slow = head;
        Node fast = head;

        // Detect loop
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                // Find loop length
                int length = 1;
                Node temp = slow.next;

                while (temp != slow) {
                    length++;
                    temp = temp.next;
                }

                // Find loop starting node
                Node p1 = head;
                Node p2 = slow;

                while (p1 != p2) {
                    p1 = p1.next;
                    p2 = p2.next;
                }

                return "Loop: yes, starts at stop " +
                       p1.data + ", length " + length;
            }
        }

        return "Loop: no";
    }

    public static void main(String[] args) {

        // Sample 1
        Node a = new Node(10);
        Node b = new Node(20);
        Node c = new Node(30);
        Node d = new Node(40);
        Node e = new Node(50);

        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        e.next = c; // pos = 2

        System.out.println(detectLoop(a));

        // Sample 2
        Node x = new Node(10);
        Node y = new Node(20);
        Node z = new Node(30);

        x.next = y;
        y.next = z;

        System.out.println(detectLoop(x));

        // Sample 3
        Node single = new Node(7);
        single.next = single; // pos = 0

        System.out.println(detectLoop(single));
    }
}
