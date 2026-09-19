package Week4;

public class W4P1 {

    static class FoodOrder {
        private String studentName;
        private String dishName;
        private boolean delivered;

        public FoodOrder(String studentName, String dishName) {
            if (studentName == null || studentName.trim().isEmpty()) {
                throw new IllegalArgumentException("Student name cannot be blank");
            }

            if (dishName == null || dishName.trim().isEmpty()) {
                throw new IllegalArgumentException("Dish name cannot be blank");
            }

            this.studentName = studentName;
            this.dishName = dishName;
            this.delivered = false;
        }

        void markDelivered() {
            if (!delivered) {
                delivered = true;
                System.out.println("Order delivered for " + studentName);
            } else {
                System.out.println("Warning: Order for " + studentName +
                        " was already marked delivered.");
            }
        }
    }

    static void processBatch(String[][] rawOrders) {
        int valid = 0;
        int rejected = 0;

        for (String[] order : rawOrders) {
            try {
                FoodOrder foodOrder =
                        new FoodOrder(order[0], order[1]);

                valid++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        System.out.println("Valid: " + valid +
                " | Rejected: " + rejected);
    }

    public static void main(String[] args) {

        String[][] rawOrders = {
            {"Ravi", "Paneer Butter Masala"},
            {"", "Chole Bhature"},
            {"Meera", " "},
            {"Divya", "Veg Biryani"}
        };

        processBatch(rawOrders);

        FoodOrder order =
                new FoodOrder("Ravi", "Paneer Butter Masala");

        order.markDelivered();
        order.markDelivered();
    }
}
