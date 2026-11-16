package Week12;

public class W12P5 {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            next = null;
        }
    }

    static Node reverse(Node head) {
        Node prev = null;
        Node curr = head;

        while (curr != null) {
            Node next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }

    static boolean isPalindrome(Node head) {
        if (head == null || head.next == null)
            return true;

        // Find end of first half
        Node slow = head;
        Node fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Reverse second half
        Node second = reverse(slow.next);
        Node temp = second;

        // Compare both halves
        boolean result = true;

        while (temp != null) {
            if (head.data != temp.data) {
                result = false;
                break;
            }

            head = head.next;
            temp = temp.next;
        }

        // Restore original list
        slow.next = reverse(second);

        return result;
    }

    static void display(Node head) {
        while (head != null) {
            System.out.print(head.data + " -> ");
            head = head.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {

        Node head = new Node(4);
        head.next = new Node(7);
        head.next.next = new Node(9);
        head.next.next.next = new Node(7);
        head.next.next.next.next = new Node(4);

        System.out.println("Palindrome: " + isPalindrome(head));
        System.out.println();
        System.out.print("List after check: ");
        display(head);

        Node head2 = new Node(4);
        head2.next = new Node(7);
        head2.next.next = new Node(7);
        head2.next.next.next = new Node(9);

        System.out.println("\nPalindrome: " + isPalindrome(head2));
        System.out.println();
        System.out.print("List after check: ");
        display(head2);
    }
}

