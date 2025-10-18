package org.example.book;

public class Book {
    private final String isbn;
    private final String title;
    private BookStatus status;
    private final String author;
    private final int publicationYear;

    public Book(String isbn, String title, String author, int publicationYear) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.status = BookStatus.AVAILABLE; // Default status
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    public String getAuthor() {
        return author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    @Override
    public String toString() {
        return "Book{" +
            "isbn='" + isbn + '\'' +
            ", title='" + title + '\'' +
            ", status=" + status +
            ", author='" + author + '\'' +
            ", publicationYear=" + publicationYear +
            '}';
    }


}