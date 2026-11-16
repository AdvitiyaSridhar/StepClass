package Week11;
import java.util.*;

public class W11P2 {

    static String expandBanner(String code) {
        Stack<Integer> counts = new Stack<>();
        Stack<StringBuilder> stack = new Stack<>();

        StringBuilder current = new StringBuilder();
        int num = 0;

        for (char ch : code.toCharArray()) {

            if (Character.isDigit(ch)) {
                num = num * 10 + (ch - '0');
            }

            else if (ch == '[') {
                counts.push(num);
                stack.push(current);

                current = new StringBuilder();
                num = 0;
            }

            else if (ch == ']') {
                int repeat = counts.pop();
                StringBuilder previous = stack.pop();

                for (int i = 0; i < repeat; i++) {
                    previous.append(current);
                }

                current = previous;
            }

            else {
                current.append(ch);
            }
        }

        return current.toString();
    }

    public static void main(String[] args) {

        String code = "3[ab]2[c]";
        System.out.println(expandBanner(code));

        String code2 = "2[x3[y]]z";
        System.out.println(expandBanner(code2));
    }
}