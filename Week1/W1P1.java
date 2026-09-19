package Week1;

public class W1P1 {

    public static void main(String[] args) {

        int[] seats = {101, 102, 103, 102, 105};

        boolean found = false;

        for (int i = 0; i < seats.length; i++) {
            for (int j = i + 1; j < seats.length; j++) {

                if (seats[i] == seats[j]) {
                    System.out.println("Duplicate Seat Number Found: " + seats[i]);
                    found = true;
                }
            }
        }

        if (!found) {
            System.out.println("No Duplicate Seats Found");
        }
    }
}