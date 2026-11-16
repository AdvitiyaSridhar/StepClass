package Week12;

public class W12P4 {

    static class Node {
        String title;
        int dueDay;
        Node next;

        Node(String title, int dueDay) {
            this.title = title;
            this.dueDay = dueDay;
            next = null;
        }
    }

    static Node sortByDueDay(Node head) {
        if (head == null || head.next == null)
            return head;

        // Find middle using slow and fast pointers
        Node slow = head;
        Node fast = head.next;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Split into two halves
        Node second = slow.next;
        slow.next = null;

        // Sort both halves
        Node left = sortByDueDay(head);
        Node right = sortByDueDay(second);

        // Merge sorted halves
        return merge(left, right);
    }

    static Node merge(Node a, Node b) {
        Node dummy = new Node("", 0);
        Node tail = dummy;

        while (a != null && b != null) {
            if (a.dueDay <= b.dueDay) {
                tail.next = a;
                a = a.next;
            } else {
                tail.next = b;
                b = b.next;
            }

            tail = tail.next;
        }

        if (a != null)
            tail.next = a;
        else
            tail.next = b;

        return dummy.next;
    }

    static void display(Node head) {
        while (head != null) {
            System.out.print(head.title + ":" + head.dueDay + " -> ");
            head = head.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        Node head = new Node("Java", 15);
        head.next = new Node("DSA", 3);
        head.next.next = new Node("OS", 9);
        head.next.next.next = new Node("DBMS", 3);
        head.next.next.next.next = new Node("CN", 12);
        head.next.next.next.next.next = new Node("AI", 1);

        head = sortByDueDay(head);
        display(head);

        // Empty list
        Node empty = null;
        empty = sortByDueDay(empty);
        display(empty);
    }
}

