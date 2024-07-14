import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        userInterface();
    }

    private static Scanner scanner(){
        return new Scanner(System.in);
    }

    private static void userInterface() {
        int id = 1;
        int choice = 1;
        Library library = new Library();
        while (choice != 0){
            System.out.println("Which operation do you want to perform: \n" +
                    "1 - Add a new book to the library.\n" +
                    "2 - View a list of all books.\n" +
                    "3 - Update details of an existing book.\n" +
                    "4 - Delete a book from the library.\n" +
                    "5 - Filter a list of books by title.\n" +
                    "6 - Filter a list of books by author.\n" +
                    "7 - Add a new author.\n" +
                    "8 - Delete an author and their books.\n" +
                    "9 - Export a list of books.\n" +
                    "0 - Quit.\n" +
                    "Your choice: ");
            choice = scanner().nextInt();
            switch (choice){
                case 1:
                    addBookToLibrary(library, id);
                    id++;
                    break;
                case 2:
                    viewAllBooks(library);
                    break;
                case 3:
                    updateDetails(library);
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Choose a correct operation");
                    break;
            }
        }
    }

    private static void addBookToLibrary(Library library, int id) {
        Book newBook = new Book();
        newBook.setId(id);

        System.out.println("Provide a title of the book: ");
        newBook.setTitle(scanner().nextLine());

        System.out.println("Provide an author: ");
        newBook.setAuthor(scanner().nextLine());

        while (true){
            System.out.println("Provide a year: ");
            int year = scanner().nextInt();
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
        scanner().nextLine();
    }

    private static void viewAllBooks(Library library) {
        library.printAllBooks();
        enterToContinue();
    }

    private static void updateDetails(Library library){
        List<Book> books = library.getBooks();
        System.out.println("Provide an id of a book you want to update: ");
        for (Book b : books){
            System.out.println(b);
        }
        int seekingId = scanner().nextInt();
        Book updatingBook = null;

        for (Book b : books){
            if (b.getId() == seekingId){
                updatingBook = b;
            }
        }
        int choice = 1;
        while (choice != 0){
            System.out.println("""
                    What do you want to update:\s
                    1 - Title
                    2 - Author
                    3 - Year
                    0 - Quit""");
            choice = scanner().nextInt();

            switch (choice){
                case 1:
                    System.out.println("Provide a new title of the book: ");
                    String newTitle = scanner().nextLine();
                    if (newTitle.equals(updatingBook.getTitle())){
                        System.out.println("Provide a new title!");
                    }
                    updatingBook.setTitle(newTitle);
                    enterToContinue();
                    break;
                case 2:
                    System.out.println("Provide a new author of the book: ");
                    String newAuthor = scanner().nextLine();
                    if (newAuthor.equals(updatingBook.getAuthor())){
                        System.out.println("Provide a new author!");
                    }
                    updatingBook.setAuthor(newAuthor);
                    enterToContinue();
                    break;
                case 3:
                    System.out.println("Provide a new year of the book: ");
                    int newYear = scanner().nextInt();
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
}