public class Book implements Comparable<Book> {
    private Integer id;
    private String title;
    private String author;
    private int year;

    public void setTitle(String title){
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

    public String getTitle(){
        return this.title;
    }

    @Override
    public String toString() {
        return "Id: " + id + ", Title: '" + title + '\'' +
                ", Author: '" + author + '\'' +
                ", Year: " + year;
    }

    public String getAuthor() {
        return this.author;
    }

    public int getYear(){
        return this.year;
    }

    @Override
    public int compareTo(Book b) {
        return this.id.compareTo(b.id);
    }
}
