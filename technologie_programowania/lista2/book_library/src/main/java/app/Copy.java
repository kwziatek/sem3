package app;

import java.util.Objects;
//High Cohesion - opisuje konkretny egezemplarz książki
public class Copy extends Book {
    private final int bookId;

    public Copy(final String title, final String author, final int yearOfPublication, final int bookId) {
        super(title, author, yearOfPublication);
        this.bookId = bookId;
    }

    public int getBookId() {
        return this.bookId;
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        final Copy copy = (Copy) object;
        return bookId == copy.bookId;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(bookId);
    }
}
