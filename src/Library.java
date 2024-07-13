import java.util.ArrayList;
import java.util.List;

public class Library {
    private final List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public void printAllBooks(){
        for (Book b : books){
            System.out.println(b);
        }
    }

    public List<Book> getBooks(){
        return books;
    }
}
