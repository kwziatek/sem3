package app;
//High Cohesion - opisuje abstrakcyjnie książke
public abstract class Book {
    private final String title;
    private final String author;
    private final int yearOfPublication;

    public Book(final String title, final String author, final int yearOfPublication) {
        this.title = title;
        this.author = author;
        this.yearOfPublication = yearOfPublication;
    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }
    @Override
    public String toString() {
        return this.title + ", " + this.author + ", " + this.yearOfPublication;
    }
}
