package Week4;

public class W4P5 {

    static class DeliveryAccount {
        static String systemName;

        static {
            systemName = "Campus Food Delivery";
        }

        protected String studentId;
        protected double orderValue;

        DeliveryAccount(String studentId, double orderValue) {
            this.studentId = studentId;
            this.orderValue = orderValue;
        }

        DeliveryAccount(String studentId) {
            this(studentId, 0);
        }

        public double calculateSurgeFee(int delayMinutes) {
            if (delayMinutes <= 0) {
                return 0;
            }

            double fee;

            if (delayMinutes <= 5) {
                fee = orderValue * 0.005 * delayMinutes;
            } else if (delayMinutes <= 15) {
                fee = orderValue * 0.005 * 5
                    + orderValue * 0.01 * (delayMinutes - 5);
            } else {
                fee = orderValue * 0.005 * 5
                    + orderValue * 0.01 * 10
                    + orderValue * 0.02 * (delayMinutes - 15);
            }

            return fee;
        }

        public double processAccount(DeliveryAccount account,
                                     double amount,
                                     int delayMinutes) {

            if (account instanceof PremiumAccount) {
                return account.calculateSurgeFee(delayMinutes) * 0.5;
            }

            return account.calculateSurgeFee(delayMinutes);
        }
    }

    static class PremiumAccount extends DeliveryAccount {

        PremiumAccount(String studentId, double orderValue) {
            super(studentId, orderValue);
        }

        @Override
        public double calculateSurgeFee(int delayMinutes) {
            return super.calculateSurgeFee(delayMinutes) * 0.5;
        }
    }

    static void processBatch(DeliveryAccount[] accounts,
                             double[] amounts,
                             int[] delays) {

        if (accounts.length != amounts.length ||
            accounts.length != delays.length) {

            System.out.println("Batch rejected: array lengths do not match.");
            return;
        }

        int processed = 0;
        int nullSkipped = 0;
        int premium = 0;
        int regular = 0;
        double grandTotal = 0;

        for (int i = 0; i < accounts.length; i++) {

            if (accounts[i] == null) {
                nullSkipped++;
                continue;
            }

            double fee;

            if (accounts[i] instanceof PremiumAccount) {
                premium++;
                fee = accounts[i].calculateSurgeFee(delays[i]);
            } else {
                regular++;
                fee = accounts[i].calculateSurgeFee(delays[i]);
            }

            grandTotal += fee;
            processed++;
        }

        System.out.println("System: " + systemName);
        System.out.println("Processed: " + processed);
        System.out.println("Null skipped: " + nullSkipped);
        System.out.println("Premium accounts: " + premium);
        System.out.println("Regular accounts: " + regular);
        System.out.println("Grand total surge fees = Rs " + grandTotal);
    }

    static String systemName = DeliveryAccount.systemName;

    public static void main(String[] args) {

        DeliveryAccount[] accounts = {
            new PremiumAccount("STU001", 500),
            null,
            new DeliveryAccount("STU002", 300)
        };

        double[] amounts = {
            500,
            400,
            300
        };

        int[] delays = {
            10,
            5,
            0
        };

        processBatch(accounts, amounts, delays);
    }
}