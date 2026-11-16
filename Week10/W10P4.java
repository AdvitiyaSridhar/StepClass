package Week10;
public class W10P4 {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    static Node reverse(Node head) {
        Node prev = null, current = head;

        while (current != null) {
            Node next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        return prev;
    }

    static boolean isPalindrome(Node head) {
        if (head == null || head.next == null)
            return true;

        Node slow = head, fast = head;

        // Find the middle
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Reverse the second half
        Node second = reverse(slow.next);
        Node temp = second;
        Node first = head;

        boolean result = true;

        // Compare both halves
        while (temp != null) {
            if (first.data != temp.data) {
                result = false;
                break;
            }

            first = first.next;
            temp = temp.next;
        }

        // Restore the original list
        slow.next = reverse(second);

        return result;
    }

    static void display(Node head) {
        while (head != null) {
            System.out.print(head.data);
            if (head.next != null)
                System.out.print(" -> ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(2);
        head.next.next.next.next = new Node(1);

        System.out.println(isPalindrome(head));
        display(head);

        Node head2 = new Node(1);
        head2.next = new Node(2);
        head2.next.next = new Node(3);
        head2.next.next.next = new Node(2);

        System.out.println(isPalindrome(head2));
        display(head2);

        Node head3 = new Node(1);
        head3.next = new Node(2);
        head3.next.next = new Node(2);
        head3.next.next.next = new Node(1);

        System.out.println(isPalindrome(head3));
        display(head3);
    }
}
