package Week11;
import java.util.*;

public class W11P1 {

    static int[] priceStreak(int[] price) {
        int n = price.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {

            while (!stack.isEmpty() && price[stack.peek()] <= price[i]) {
                stack.pop();
            }

            if (stack.isEmpty())
                result[i] = i + 1;
            else
                result[i] = i - stack.peek();

            stack.push(i);
        }

        return result;
    }

    public static void main(String[] args) {
        int[] price = {100, 80, 60, 70, 60, 75, 85};

        System.out.println(Arrays.toString(priceStreak(price)));

        int[] price2 = {10, 20, 30};

        System.out.println(Arrays.toString(priceStreak(price2)));
    }
}