package Week12;

public class W12P2 {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            next = null;
        }
    }

    static Node reverseInGroups(Node head, int k) {
        Node dummy = new Node(0);
        dummy.next = head;

        Node prevGroup = dummy;

        while (true) {
            Node kth = prevGroup;

            // Check whether k nodes exist
            for (int i = 0; i < k && kth != null; i++) {
                kth = kth.next;
            }

            if (kth == null) break;

            Node groupNext = kth.next;
            Node prev = groupNext;
            Node curr = prevGroup.next;

            // Reverse k nodes
            while (curr != groupNext) {
                Node next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }

            Node oldGroupHead = prevGroup.next;
            prevGroup.next = kth;
            prevGroup = oldGroupHead;
        }

        return dummy.next;
    }

    static void display(Node head) {
        while (head != null) {
            System.out.print(head.data + " -> ");
            head = head.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);
        head.next.next.next.next.next.next = new Node(7);
        head.next.next.next.next.next.next.next = new Node(8);

        head = reverseInGroups(head, 3);
        display(head);

        Node head2 = new Node(11);
        head2.next = new Node(12);

        head2 = reverseInGroups(head2, 3);
        display(head2);
    }
}
