import java.util.ArrayList;
import java.util.Scanner;

class Book {
    private int id;
    private String title;
    private String author;
    private boolean isIssued;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isIssued() {
        return isIssued;
    }

    public void issueBook() {
        this.isIssued = true;
    }

    public void returnBook() {
        this.isIssued = false;
    }

    public void display() {
        System.out.println("ID: " + id +
                ", Title: " + title +
                ", Author: " + author +
                ", Issued: " + (isIssued ? "Yes" : "No"));
    }
}

public class LibraryManagement {

    static ArrayList<Book> books = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n===== Library Management System =====");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Search Book");
            System.out.println("6. Delete Book");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");

            if (!sc.hasNextInt()) {
                System.out.println("Invalid input! Enter a number.");
                sc.next();
                continue;
            }

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    viewBooks();
                    break;
                case 3:
                    issueBook();
                    break;
                case 4:
                    returnBook();
                    break;
                case 5:
                    searchBook();
                    break;
                case 6:
                    deleteBook();
                    break;
                case 7:
                    System.out.println("Thank you!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    static boolean bookExists(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return true;
            }
        }
        return false;
    }

    static Book findBookById(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    static void addBook() {
        System.out.print("Enter Book ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        if (bookExists(id)) {
            System.out.println("Book ID already exists!");
            return;
        }

        System.out.print("Enter Title: ");
        String title = sc.nextLine();

        System.out.print("Enter Author: ");
        String author = sc.nextLine();

        books.add(new Book(id, title, author));
        System.out.println("Book added successfully!");
    }

    static void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }

        for (Book book : books) {
            book.display();
        }
    }

    static void issueBook() {
        System.out.print("Enter Book ID to issue: ");
        int id = sc.nextInt();

        Book book = findBookById(id);

        if (book == null) {
            System.out.println("Book not found!");
        } else if (!book.isIssued()) {
            book.issueBook();
            System.out.println("Book issued successfully!");
        } else {
            System.out.println("Book already issued!");
        }
    }

    static void returnBook() {
        System.out.print("Enter Book ID to return: ");
        int id = sc.nextInt();

        Book book = findBookById(id);

        if (book == null) {
            System.out.println("Book not found!");
        } else if (book.isIssued()) {
            book.returnBook();
            System.out.println("Book returned successfully!");
        } else {
            System.out.println("Book was not issued!");
        }
    }

    static void searchBook() {
        sc.nextLine();
        System.out.print("Enter Book Title to search: ");
        String title = sc.nextLine();

        boolean found = false;

        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                book.display();
                found = true;
            }
        }

        if (!found) {
            System.out.println("Book not found!");
        }
    }

    static void deleteBook() {
        System.out.print("Enter Book ID to delete: ");
        int id = sc.nextInt();

        Book book = findBookById(id);

        if (book == null) {
            System.out.println("Book not found!");
        } else {
            books.remove(book);
            System.out.println("Book deleted successfully!");
        }
    }
}