
public class Main {

    
    interface Loanable {
        void checkOut();
        void checkIn();
    }

    interface Notifiable {
        void sendNotification(String message);
    }

    abstract static class User {
        protected String name;
        protected int userId;

        public User() {
            this.name = "Unknown";
            this.userId = 0;
        }

        public User(String name, int userId) {
            this.name = name;
            this.userId = userId;
        }

        public abstract void displayInfo();

        public String getName() {
            return name;
        }
    }

    abstract static class Media {
        protected String title;
        protected String author;

        public Media(String title, String author) {
            this.title = title;
            this.author = author;
        }

        public abstract void displayDetails();

        public String getTitle() {
            return title;
        }
    }

    static final class Librarian extends User {
        private String librarySection;

        public Librarian() {
            super();
            this.librarySection = "General";
        }

        public Librarian(String name, int userId, String section) {
            super(name, userId);
            this.librarySection = section;
        }

        @Override
        public void displayInfo() {
            System.out.println("Librarian: " + name + ", Section: " + librarySection);
        }

        public void manageInventory() {
            System.out.println("Managing inventory...");
        }
    }

    static class Member extends User implements Notifiable {
        private String membershipType;

        public Member() {
            super();
            this.membershipType = "Basic";
        }

        public Member(String name, int userId, String membershipType) {
            super(name, userId);
            this.membershipType = membershipType;
        }

        @Override
        public void displayInfo() {
            System.out.println("Member: " + name + ", Membership: " + membershipType);
        }

        @Override
        public void sendNotification(String message) {
            System.out.println("Notification to " + name + ": " + message);
        }
    }

    static class Book extends Media implements Loanable {
        private String genre;

        public Book(String title, String author, String genre) {
            super(title, author);
            this.genre = genre;
        }

        @Override
        public void displayDetails() {
            System.out.println("Book: " + title + " by " + author + ", Genre: " + genre);
        }

        @Override
        public void checkOut() {
            System.out.println("Checking out book: " + title);
        }

        @Override
        public void checkIn() {
            System.out.println("Returning book: " + title);
        }
    }

    static class Magazine extends Media {
        private int issueNumber;

        public Magazine(String title, String author, int issueNumber) {
            super(title, author);
            this.issueNumber = issueNumber;
        }

        @Override
        public void displayDetails() {
            System.out.println("Magazine: " + title + ", Issue: " + issueNumber);
        }
    }

    static class Notification {
        public static void sendEmail(String message) {
            System.out.println("Sending email: " + message);
        }
    }


    static final class Constants {
        public static final String LIBRARY_NAME = "City Library";
    }

    static class Library {
        private Librarian librarian;
        private Member member;

        public Library(Librarian librarian, Member member) {
            this.librarian = librarian;
            this.member = member;
        }

        public void showInfo() {
            System.out.println("Welcome to " + Constants.LIBRARY_NAME);
            librarian.displayInfo();
            member.displayInfo();
        }
    }

    static class Loan {
        public void processLoan(Book book) {
            book.checkOut();
        }
    }


    public static void main(String[] args) {
        Librarian librarian = new Librarian("Alice", 101, "Fiction");
        Member member = new Member("Bob", 201, "Premium");
        Book book = new Book("Java Programming", "John Doe", "Education");
        Magazine magazine = new Magazine("Tech Today", "Jane Smith", 5);

        Library library = new Library(librarian, member);
        library.showInfo();

        Loan loan = new Loan();
        loan.processLoan(book);

        book.displayDetails();
        magazine.displayDetails();

        member.sendNotification("Your book is due soon!");
        Notification.sendEmail("Library newsletter");
    }
}
