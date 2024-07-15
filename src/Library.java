import java.util.ArrayList;
import java.util.List;

public class Library {
    private final List<Book> books = new ArrayList<>();
    private final List<Author> authors = new ArrayList<>();

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

    public List<Author> getAuthors() {
        return authors;
    }

    public void addAuthor(Author author) {
        authors.add(author);
    }
}
