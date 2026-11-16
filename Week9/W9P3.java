import java.util.*;

public class W9P3 {

    static long countPeriods(int[] transactions, int k) {
        HashMap<Long, Integer> map = new HashMap<>();

        long sum = 0, count = 0;
        map.put(0L, 1);

        for (int num : transactions) {
            sum += num;

            count += map.getOrDefault(sum - k, 0);

            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return count;
    }

    public static void main(String[] args) {
        int[] transactions = {3, 4, -7, 1, 3, 3, 1, -4};
        int k = 7;

        System.out.println(countPeriods(transactions, k));

        int[] transactions2 = {1, 2, 3};

        System.out.println(countPeriods(transactions2, 10));
    }
}