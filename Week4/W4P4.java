package Week4;

public final class W4P4 {

    private final double minimumSurgePercent;

    public W4P4(double minimumSurgePercent) {
        this.minimumSurgePercent = minimumSurgePercent;
    }

    public final double calculateSurgeFee(double orderValue,
                                          int delayMinutes) {

        if (orderValue < 0) {
            throw new IllegalArgumentException(
                    "Order value cannot be negative");
        }

        if (delayMinutes < 0) {
            throw new IllegalArgumentException(
                    "Delay minutes cannot be negative");
        }

        if (delayMinutes == 0) {
            return 0;
        }

        double surgeFee = 0;

        int firstTier = Math.min(delayMinutes, 5);
        surgeFee += firstTier * orderValue * 0.005;

        if (delayMinutes > 5) {
            int secondTier =
                    Math.min(delayMinutes - 5, 10);

            surgeFee += secondTier * orderValue * 0.01;
        }

        if (delayMinutes > 15) {
            int thirdTier = delayMinutes - 15;

            surgeFee += thirdTier * orderValue * 0.02;
        }

        double minimumFee =
                orderValue * minimumSurgePercent / 100.0;

        return Math.max(surgeFee, minimumFee);
    }

    public static void main(String[] args) {

        W4P4 calculator = new W4P4(1.0);

        System.out.println("Rs " +
                calculator.calculateSurgeFee(500, 0));

        System.out.println("Rs " +
                calculator.calculateSurgeFee(500, 1));

        System.out.println("Rs " +
                calculator.calculateSurgeFee(500, 16));
    }
}