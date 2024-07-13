public class Book {
    private int id;
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
}
