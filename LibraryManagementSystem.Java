import java.util.ArrayList;
import java.util.List;

// Book class to store details about books in the library
class Book {
    private int bookId;
    private String title;
    private String author;
    private int quantity;

    public Book(int bookId, String title, String author, int quantity) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.quantity = quantity;
    }

    public String getBookInfo() {
        return "Book ID: " + bookId + ", Title: " + title + ", Author: " + author + ", Available: " + quantity;
    }

    public void updateQuantity(int quantity) {
        this.quantity += quantity;
    }

    public boolean isAvailable() {
        return quantity > 0;
    }

    public void decreaseQuantity() {
        if (quantity > 0) {
            quantity--;
        }
    }

    public void increaseQuantity() {
        quantity++;
    }
}

// User class to store user details
class User {
    private int userId;
    private String name;
    private List<Book> borrowedBooks;

    public User(int userId, String name) {
        this.userId = userId;
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void borrowBook(Book book) {
        if (book.isAvailable()) {
            borrowedBooks.add(book);
            book.decreaseQuantity();
            System.out.println(name + " borrowed " + book.getBookInfo());
        } else {
            System.out.println("Sorry, " + book.getBookInfo() + " is out of stock.");
        }
    }

    public void returnBook(Book book) {
        if (borrowedBooks.contains(book)) {
            borrowedBooks.remove(book);
            book.increaseQuantity();
            System.out.println(name + " returned " + book.getBookInfo());
        } else {
            System.out.println(name + " has not borrowed " + book.getBookInfo());
        }
    }

    public void displayBorrowedBooks() {
        if (borrowedBooks.isEmpty()) {
            System.out.println(name + " has not borrowed any books.");
        } else {
            System.out.println(name + " has borrowed the following books:");
            for (Book book : borrowedBooks) {
                System.out.println("- " + book.getBookInfo());
            }
        }
    }
}

// Library class to manage books and users
class Library {
    private List<Book> books;
    private List<User> users;

    public Library() {
        books = new ArrayList<>();
        users = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void registerUser(User user) {
        users.add(user);
    }

    public Book findBook(int bookId) {
        for (Book book : books) {
            if (bookId == book.bookId) {
                return book;
            }
        }
        return null;
    }

    public void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available in the library.");
        } else {
            System.out.println("Books available in the library:");
            for (Book book : books) {
                System.out.println(book.getBookInfo());
            }
        }
    }

    public void displayUsers() {
        if (users.isEmpty()) {
            System.out.println("No users registered.");
        } else {
            System.out.println("Registered users:");
            for (User user : users) {
                System.out.println(user.getName());
            }
        }
    }
}

// Main class to run the system
public class LibraryManagementSystem {
    public static void main(String[] args) {
        // Create library instance
        Library library = new Library();

        // Create books and add to library
        Book book1 = new Book(1, "The Alchemist", "Paulo Coelho", 5);
        Book book2 = new Book(2, "1984", "George Orwell", 2);
        library.addBook(book1);
        library.addBook(book2);

        // Create users and register them in library
        User user1 = new User(1, "Alice");
        User user2 = new User(2, "Bob");
        library.registerUser(user1);
        library.registerUser(user2);

        // Display available books
        library.displayBooks();

        // Users borrow books
        user1.borrowBook(book1);  // Alice borrows The Alchemist
        user2.borrowBook(book2);  // Bob borrows 1984

        // Display borrowed books
        user1.displayBorrowedBooks();
        user2.displayBorrowedBooks();

        // User returns book
        user1.returnBook(book1);  // Alice returns The Alchemist
        user2.returnBook(book2);  // Bob returns 1984

        // Display available books again
        library.displayBooks();
    }
}
