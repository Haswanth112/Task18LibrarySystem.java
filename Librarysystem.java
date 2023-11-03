import java.util.*;
class Book {
    private String title;
    public Book(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
class Magazine extends Book {
    public Magazine(String title) {
        super(title);
    }
}
class DuplicateBookException extends Exception {
    public DuplicateBookException(String message) {
        super(message);
    }
}
class DuplicateMagazineException extends Exception {
    public DuplicateMagazineException(String message) {
        super(message);
    }
}
class InvalidBookException extends Exception {
    public InvalidBookException(String message) {
        super(message);
    }
}
class InvalidMagazineException extends Exception {
    public InvalidMagazineException(String message) {
        super(message);
    }
}
class Library {
    private List<Book> books;

    public Library() {
        books = new ArrayList<>();
    }
    public void addBook(Book newBook) throws DuplicateBookException, InvalidBookException {
        if (newBook instanceof Magazine) {
            throw new InvalidBookException("Magazines cannot be added as Books");
        }
        for (Book book : books) {
            if (book.getTitle().equals(newBook.getTitle())) {
                throw new DuplicateBookException("Duplicate book found");
            }
        }
        books.add(newBook);
        System.out.println("Book added: " + newBook.getTitle());
    }
    public void addMagazine(Magazine newMagazine) throws DuplicateMagazineException, InvalidMagazineException {
        if (newMagazine instanceof Book) {
            throw new InvalidMagazineException("Books cannot be added as Magazines");
        }
        for (Book magazine : books) {
            if (magazine.getTitle().equals(newMagazine.getTitle())) {
                throw new DuplicateMagazineException("Duplicate magazine found");
            }
        }
        books.add(newMagazine);
        System.out.println("Magazine added: " + newMagazine.getTitle());
    }
    public void removeBook(String title) {
        books.removeIf(book -> book.getTitle().equals(title));
        System.out.println("Book removed: " + title);
    }
    public void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("The library has no books at the moment.");
        } else {
            System.out.println("Books in the library:");
            for (Book book : books) {
                System.out.println("- " + book.getTitle());
            }
        }
    }
}
public class Librarysystem {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add a Book");
            System.out.println("2. Add a Magazine");
            System.out.println("3. Remove a Book");
            System.out.println("4. Display Books");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Enter book title: ");
                    String bookTitle = scanner.nextLine();
                    Book newBook = new Book(bookTitle);
                    try {
                        library.addBook(newBook);
                    } catch (DuplicateBookException | InvalidBookException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.print("Enter magazine title: ");
                    String magazineTitle = scanner.nextLine();
                    Magazine newMagazine = new Magazine(magazineTitle);
                    try {
                        library.addMagazine(newMagazine);
                    } catch (DuplicateMagazineException | InvalidMagazineException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 3:
                    System.out.print("Enter the title of the book to remove: ");
                    String titleToRemove = scanner.nextLine();
                    library.removeBook(titleToRemove);
                    break;
                case 4:
                    library.displayBooks();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        }
    }
}
