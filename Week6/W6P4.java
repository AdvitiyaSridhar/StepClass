package Week6;

public class W6P4 {

    static class EventTicket {

        protected double basePrice;
        protected double amountPaid;

        public EventTicket(double basePrice) {
            this.basePrice = basePrice;
        }

        public double getBalanceDue() {
            return basePrice - amountPaid;
        }

        public void printTicket() {
            System.out.println(
                "Standard | Balance: "
                + getBalanceDue()
            );
        }
    }

    static class WorkshopTicket extends EventTicket {

        private String track;

        public WorkshopTicket(double basePrice,
                              String track) {

            super(basePrice);
            this.track = track;
        }

        public String getTrack() {
            return track;
        }

        @Override
        public void printTicket() {
            System.out.println(
                "Workshop | Track: "
                + track
                + " | Balance: "
                + getBalanceDue()
            );
        }
    }

    static String batchPrint(EventTicket[] tickets) {

        StringBuilder result =
            new StringBuilder();

        for (EventTicket ticket : tickets) {

            if (ticket instanceof WorkshopTicket) {

                WorkshopTicket workshop =
                    (WorkshopTicket) ticket;

                result.append("Workshop | Track: ")
                      .append(workshop.getTrack())
                      .append(" | Balance: ")
                      .append(workshop.getBalanceDue());

                result.append(
                    " [Track via downcast: "
                )
                .append(workshop.getTrack())
                .append("] | ");

            } else {

                result.append("Standard | Balance: ")
                      .append(ticket.getBalanceDue())
                      .append(" | ");
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {

        EventTicket[] tickets = {

            new EventTicket(500),

            new WorkshopTicket(
                1200,
                "AI/ML"
            )
        };

        System.out.println(
            batchPrint(tickets)
        );

        EventTicket plain =
            new EventTicket(500);

        /*
        This would cause ClassCastException:

        WorkshopTicket bad =
            (WorkshopTicket) plain;
        */
    }
}