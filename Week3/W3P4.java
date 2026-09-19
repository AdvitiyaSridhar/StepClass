package Week3;

public class W3P4 {

    static class LibraryMember {

        String name;
        String memberId;
        int booksIssued;

        static String libraryName = "Central Library";
        static int memberCount = 0;

        LibraryMember(String name, int booksIssued) {
            this.name = name;
            this.booksIssued = booksIssued;

            memberCount++;

            this.memberId = "LM-" + (1000 + memberCount);
        }

        void printMemberCard() {
            System.out.println(name + " | " + memberId);
        }

        static void printTotalMembers() {
            System.out.println("Total members: " + memberCount);
        }
    }

    public static void main(String[] args) {

        System.out.println("Broken version:");

        BrokenMember member1 = new BrokenMember("Aditi", "LM-1001", 2);
        BrokenMember member2 = new BrokenMember("Rohan", "LM-1002", 3);

        System.out.println(member1.name);
        System.out.println(member2.name);

        System.out.println();
        System.out.println("Fixed version:");

        LibraryMember.memberCount = 0;

        LibraryMember m1 = new LibraryMember("Aditi", 2);
        LibraryMember m2 = new LibraryMember("Rohan", 3);

        m1.printMemberCard();
        m2.printMemberCard();

        LibraryMember.printTotalMembers();
    }

    /*
     * In the broken version, name, memberId and booksIssued are static.
     * Therefore, they are shared by every LibraryMember object.
     * When the second object changes them, the first object's data is overwritten.
     *
     * In the fixed version, name, memberId and booksIssued are instance fields,
     * because each member must have separate values.
     *
     * libraryName and memberCount are static because they are shared
     * by the entire library.
     */

    static class BrokenMember {

        static String name;
        static String memberId;
        static int booksIssued;

        BrokenMember(String name, String memberId, int booksIssued) {
            BrokenMember.name = name;
            BrokenMember.memberId = memberId;
            BrokenMember.booksIssued = booksIssued;
        }
    }
}