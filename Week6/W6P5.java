package Week6;

public class W6P5 {

    static class EventTicket {

        private static int ticketCounter = 1000;

        final String ticketId;

        protected double basePrice;
        protected double amountPaid;
        protected String paymentMode;

        public EventTicket(double basePrice) {

            this.basePrice = basePrice;
            this.amountPaid = 0;

            ticketCounter++;

            this.ticketId =
                "TCK-" + ticketCounter;
        }

        public void pay(double amount) {
            amountPaid += amount;
        }

        public void pay(double amount,
                        String mode) {

            System.out.println(
                "Payment mode: " + mode
            );

            paymentMode = mode;

            pay(amount);
        }

        public double getBalanceDue() {
            return basePrice - amountPaid;
        }

        public static boolean isValidPromoCode(
                String code) {

            if (code == null ||
                code.length() != 5) {

                return false;
            }

            if (code.charAt(0) != 'F') {
                return false;
            }

            if (!Character.isDigit(code.charAt(1)) ||
                !Character.isDigit(code.charAt(2)) ||
                !Character.isDigit(code.charAt(3))) {

                return false;
            }

            if (!Character.isUpperCase(code.charAt(4))) {
                return false;
            }

            return true;
        }

        public static int getTicketsIssued() {
            return ticketCounter - 1000;
        }
    }

    static class GroupTicket extends EventTicket {

        private int groupSize;

        public GroupTicket(double basePrice,
                           int groupSize) {

            super(basePrice);
            this.groupSize = groupSize;
        }
    }

    static String processNightlySettlement(
            EventTicket[] tickets) {

        int processed = 0;
        int skipped = 0;
        int groupTickets = 0;
        int regularTickets = 0;

        for (EventTicket ticket : tickets) {

            if (ticket == null) {
                skipped++;
                continue;
            }

            processed++;

            if (ticket instanceof GroupTicket) {
                groupTickets++;
            } else {
                regularTickets++;
            }
        }

        return processed + " processed | "
             + skipped + " null skipped | "
             + groupTickets + " group | "
             + regularTickets + " regular";
    }

    public static void main(String[] args) {

        EventTicket t1 =
            new EventTicket(500);

        System.out.println(t1.ticketId);

        System.out.println(
            EventTicket.getTicketsIssued()
        );

        System.out.println(
            EventTicket.isValidPromoCode("F123A")
        );

        System.out.println(
            EventTicket.isValidPromoCode("F12A")
        );

        System.out.println(
            EventTicket.isValidPromoCode("X123A")
        );

        t1.pay(200);
        t1.pay(200, "UPI");

        System.out.println(
            t1.getBalanceDue()
        );

        EventTicket[] tickets = {

            t1,

            new GroupTicket(1000, 5),

            null,

            new EventTicket(800)
        };

        System.out.println(
            processNightlySettlement(tickets)
        );
    }
}