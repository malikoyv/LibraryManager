import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;


public class Main {
    private static final Scanner scannerInt = new Scanner(System.in);
    private static final Scanner scannerString = new Scanner(System.in);
    private static final Library library = new Library();
    
    public static void main(String[] args) {
        userInterface();
    }

    private static void userInterface() {
        int bookId = 1;
        int authorId = 1;
        int choice = 1;

        while (choice != 0){
            System.out.println("Which operation do you want to perform: \n" +
                    "1 - Add a new book to the library.\n" +
                    "2 - View a list of all books.\n" +
                    "3 - Update details of an existing book.\n" +
                    "4 - Delete a book from the library.\n" +
                    "5 - Filter a list of books by title or author.\n" +
                    "6 - Add a new author.\n" +
                    "7 - Delete an author and their books.\n" +
                    "8 - Export a list of books.\n" +
                    "0 - Quit.\n" +
                    "Your choice: ");
            choice = scannerInt.nextInt();

            switch (choice){
                case 1:
                    addBookToLibrary(bookId);
                    bookId++;
                    break;
                case 2:
                    viewAllBooks();
                    break;
                case 3:
                    updateDetails();
                    break;
                case 4:
                    deleteBook();
                    break;
                case 5:
                    filterBooks();
                    break;
                case 6:
                    addAuthor(authorId);
                    authorId++;
                    break;
                case 7:
                    deleteAuthorAndBooks();
                    break;
                case 8:
                    exportBooksToCSV();
                    break;
                case 0:
                    System.out.println("Quiting...");
                    break;
                default:
                    System.out.println("Choose a correct operation");
                    break;
            }
        }
    }

    private static void addBookToLibrary(int id) {
        Book newBook = new Book();
        newBook.setId(id);

        System.out.println("Provide a title of the book: ");
        String title = scannerString.nextLine().trim();
        newBook.setTitle(title);

        System.out.println("Choose an author: ");
        List<Author> authors = library.getAuthors();
        Author selected;
        if (authors.isEmpty()){
            System.out.println("No authors available. Add a new author.");
            addAuthor(1);
            selected = library.getAuthors().getFirst();
        } else {
            selected = showAuthorsAndAskId(authors);
        }

        if (selected == null){
            System.out.println("Invalid author ID. Try add new author: ");
            addAuthor(authors.size());
            selected = library.getAuthors().get(authors.size() - 1);
        }
        newBook.setAuthor(selected);

        while (true){
            System.out.println("Provide a year: ");
            int year = scannerInt.nextInt();
            if (year > LocalDate.now().getYear() || year < 0){
                System.out.println("Provide a correct year");
            } else {
                newBook.setYear(year);
                break;
            }
        }

        System.out.println("Book information: ");
        System.out.println(newBook);

        library.addBook(newBook);
        System.out.println("Book was successfully added!\n");
        enterToContinue();
    }

    private static void enterToContinue() {
        System.out.println("Press Enter to continue.");
        scannerString.nextLine();
    }

    private static void viewAllBooks() {
        library.printAllBooks();
        enterToContinue();
    }

    private static void updateDetails(){
        List<Book> books = library.getBooks();
        System.out.println("Provide an id of a book you want to update: ");
        Book updatingBook = seekBook(books);
        int choice = 1;
        while (choice != 0){
            System.out.println("""
                    What do you want to update:\s
                    1 - Title
                    2 - Author
                    3 - Year
                    0 - Quit""");
            choice = scannerInt.nextInt();

            switch (choice){
                case 1:
                    System.out.println("Provide a new title of the book: ");
                    String newTitle = scannerString.nextLine().trim();
                    if (newTitle.equals(updatingBook.getTitle())){
                        System.out.println("Provide a new title!");
                    }
                    updatingBook.setTitle(newTitle);
                    enterToContinue();
                    break;
                case 2:
                    System.out.println("Provide a new author of the book: ");
                    List<Author> authors = library.getAuthors();
                    if (authors.isEmpty()){
                        System.out.println("No authors available. Add a new author.");
                        addAuthor(1);
                    }

                    updatingBook.setAuthor(showAuthorsAndAskId(authors));
                    enterToContinue();
                    break;
                case 3:
                    System.out.println("Provide a new year of the book: ");
                    int newYear = scannerInt.nextInt();
                    if (newYear == updatingBook.getYear()){
                        System.out.println("Provide a new author!");
                    }
                    updatingBook.setYear(newYear);
                    enterToContinue();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Choose a correct operation!");
                    enterToContinue();
                    break;
            }
        }
    }

    private static Book seekBook(List<Book> books) {
        for (Book b : books){
            System.out.println(b);
        }
        int seekingId = scannerInt.nextInt();
        Book updatingBook = null;
        for (Book b : books){
            if (b.getId() == seekingId){
                updatingBook = b;
            }
        }
        return updatingBook;
    }

    private static void deleteBook() {
        List<Book> books = library.getBooks();
        System.out.println("Provide an id of a book you want to delete: ");
        Book updatingBook = seekBook(books);
        library.getBooks().remove(updatingBook);
        System.out.println("Book was successfully deleted!");
        enterToContinue();
    }

    private static void filterBooks(){
        List<Book> books = library.getBooks();
        List<Author> authors = library.getAuthors();
        int choice = 1;
        List<Book> sortedList = new ArrayList<>();

        while (choice != 0){
            System.out.println("""
                What do you want to filter by:\s
                1 - Author.
                2 - Title.
                0 - Quit.""");
            choice = scannerInt.nextInt();

            switch (choice){
                case 1:
                    System.out.println("Choose an author: ");
                    for (Author a : authors){
                        System.out.println(a);
                    }
                    System.out.println("Enter author ID: ");
                    int authorId = scannerInt.nextInt();
                    boolean found = false;

                    for (Author a : authors){
                        if (a.getId() == authorId){
                            for (Book b : books){
                                if (b.getAuthor() == a){
                                    System.out.println(b);
                                }
                            }
                            found = true;
                            break;
                        }
                    }
                    if (!found){
                        System.out.println("Invalid author ID. Try again");
                        break;
                    }

                    Collections.sort(sortedList);
                    for (Book b : sortedList){
                        System.out.println(b);
                    }
                    sortedList.clear();
                    enterToContinue();
                    break;
                case 2:
                    System.out.println("Enter title: ");
                    String title = scannerString.nextLine().trim();
                    for (Book b : books){
                        if (b.getTitle().equalsIgnoreCase(title)){
                            sortedList.add(b);
                        }
                    }
                    Collections.sort(sortedList);
                    for (Book b : sortedList){
                        System.out.println(b);
                    }
                    sortedList.clear();
                    enterToContinue();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid choice, please try again!");
                    break;
            }
        }
    }

    private static Author showAuthorsAndAskId(List<Author> authors) {
        for (Author a : authors){
            System.out.println(a);
        }

        System.out.println("Enter author ID: ");
        int authorId = scannerInt.nextInt();
        Author selected = null;
        for (Author a : authors){
            if (a.getId() == authorId){
                selected = a;
                break;
            }
        }
        return selected;
    }

    private static void addAuthor(int id) {
        Author newAuthor = new Author();
        System.out.println("Provide a name of the author: ");
        newAuthor.setName(scannerString.nextLine().trim());
        newAuthor.setId(id);

        library.addAuthor(newAuthor);
        System.out.println("New author was successfully added!");
        enterToContinue();
    }

    private static void deleteAuthorAndBooks(){
        List<Book> books = library.getBooks();

        Author selected = showAuthorsAndAskId(library.getAuthors());
        if (selected != null) {
            books.removeIf(book -> book.getAuthor().equals(selected));
            library.getAuthors().remove(selected);
            System.out.println("Author and their books were successfully deleted!");
        } else {
            System.out.println("Author not found!");
        }
        enterToContinue();
    }

    private static void exportBooksToCSV() {
        String csvFile = "books.csv";
        List<Book> books = library.getBooks();

        try (FileWriter writer = new FileWriter(csvFile)) {
            writer.append("ID,Title,Author,Year\n");

            for (Book book : books) {
                writer.append(String.valueOf(book.getId())).append(",");
                writer.append(book.getTitle()).append(",");
                writer.append(book.getAuthor().getName()).append(",");
                writer.append(String.valueOf(book.getYear())).append("\n");
            }

            System.out.println("Books have been successfully exported to " + csvFile);
            enterToContinue();
        } catch (IOException e) {
            System.err.println("Error while exporting books to CSV: " + e.getMessage());
        }
    }
}