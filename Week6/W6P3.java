package Week6;

public class W6P3 {

    static class EventTicket {

        protected double basePrice;
        protected double amountPaid;

        private double[] lateFeeHistory;
        private int lateFeeCount;

        public EventTicket(double basePrice) {
            this.basePrice = basePrice;
            this.amountPaid = 0;
            this.lateFeeHistory = new double[10];
            this.lateFeeCount = 0;
        }

        public void pay(double amount) {
            amountPaid += amount;
        }

        public double getBalanceDue() {
            double totalLateFees = 0;

            for (int i = 0; i < lateFeeCount; i++) {
                totalLateFees += lateFeeHistory[i];
            }

            return basePrice - amountPaid + totalLateFees;
        }

        protected void applyLateFee(double amount) {

            if (lateFeeCount < lateFeeHistory.length) {
                lateFeeHistory[lateFeeCount] = amount;
                lateFeeCount++;
            }
        }

        public double[] getLateFeeHistory() {

            double[] result =
                new double[lateFeeCount];

            for (int i = 0; i < lateFeeCount; i++) {
                result[i] = lateFeeHistory[i];
            }

            return result;
        }
    }

    static class WorkshopTicket extends EventTicket {

        public WorkshopTicket(double basePrice) {
            super(basePrice);
        }

        @Override
        protected void applyLateFee(double amount) {
            super.applyLateFee(amount * 2);
        }
    }

    public static void main(String[] args) {

        WorkshopTicket w =
            new WorkshopTicket(1200);

        w.pay(1200);

        w.applyLateFee(100);

        System.out.println(
            w.getBalanceDue()
        );

        double[] history =
            w.getLateFeeHistory();

        System.out.println(history[0]);

        history[0] = 999;

        System.out.println(
            w.getLateFeeHistory()[0]
        );
    }
}