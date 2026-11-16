package Week10;
public class W10P5 {

    static class Node {
        int coeff, exp;
        Node next;

        Node(int coeff, int exp) {
            this.coeff = coeff;
            this.exp = exp;
        }
    }

    static Node addPolynomials(Node p, Node q) {
        Node dummy = new Node(0, 0);
        Node tail = dummy;

        while (p != null && q != null) {
            if (p.exp > q.exp) {
                tail.next = new Node(p.coeff, p.exp);
                p = p.next;
            } 
            else if (q.exp > p.exp) {
                tail.next = new Node(q.coeff, q.exp);
                q = q.next;
            } 
            else {
                int sum = p.coeff + q.coeff;

                if (sum != 0)
                    tail.next = new Node(sum, p.exp);

                p = p.next;
                q = q.next;

                if (sum == 0)
                    continue;
            }

            if (tail.next != null)
                tail = tail.next;
        }

        while (p != null) {
            tail.next = new Node(p.coeff, p.exp);
            tail = tail.next;
            p = p.next;
        }

        while (q != null) {
            tail.next = new Node(q.coeff, q.exp);
            tail = tail.next;
            q = q.next;
        }

        return dummy.next;
    }

    static void display(Node head) {
        boolean first = true;

        while (head != null) {
            if (!first && head.coeff > 0)
                System.out.print(" + ");
            else if (!first && head.coeff < 0)
                System.out.print(" - ");
            else if (first && head.coeff < 0)
                System.out.print("-");

            int c = Math.abs(head.coeff);

            if (head.exp == 0)
                System.out.print(c);
            else {
                if (c != 1)
                    System.out.print(c);

                System.out.print("x");

                if (head.exp != 1)
                    System.out.print("^" + head.exp);
            }

            first = false;
            head = head.next;
        }

        if (first)
            System.out.print("0");

        System.out.println();
    }

    public static void main(String[] args) {

        // P = 5x^3 + 4x^2 + 2
        Node p = new Node(5, 3);
        p.next = new Node(4, 2);
        p.next.next = new Node(2, 0);

        // Q = 5x^2 - 3x + 1
        Node q = new Node(5, 2);
        q.next = new Node(-3, 1);
        q.next.next = new Node(1, 0);

        Node result = addPolynomials(p, q);
        display(result);

        // Second example
        Node a = new Node(3, 2);
        a.next = new Node(1, 0);

        Node b = new Node(-3, 2);
        b.next = new Node(4, 1);

        Node result2 = addPolynomials(a, b);
        display(result2);
    }
}