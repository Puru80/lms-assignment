package org.example;

import org.example.book.Book;
import org.example.book.BookStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class InventoryService {

    private static final Logger log = LoggerFactory.getLogger(InventoryService.class);
    private final Map<String, Book> books = new HashMap<>();

    /**
     * Adds a new item to the inventory.
     *
     * @param book The Book to add.
     * @throws IllegalStateException if a book with the same ISBN already exists.
     * @throws IllegalArgumentException if the book is null.
     */
    public void addItem(Book book) {
        Objects.requireNonNull(book, "Book cannot be null");

        if (books.containsKey(book.getIsbn())) {
            log.warn("Attempted to add a duplicate item with ID: {}", book.getIsbn());
            throw new IllegalStateException("Book with ISBN " + book.getIsbn() + " already exists.");
        }

        books.put(book.getIsbn(), book);
        log.info("Successfully added item: {}", book);
    }

    /**
     * Retrieves a library item by its unique ID.
     *
     * @param itemId The ID of the item to find.
     * @return an Optional containing the LibraryItem if found, otherwise an empty Optional.
     */
    public Optional<Book> getBookByIsbn(String itemId) {
        return Optional.ofNullable(books.get(itemId));
    }

    /**
     * Updates the status of a specific library item.
     *
     * @param isbn The ID of the book to update.
     * @param status The new status for the item.
     * @return true if the update was successful, false if the item was not found.
     */
    public boolean updateItemStatus(String isbn, BookStatus status) {
        Optional<Book> bookOptional = getBookByIsbn(isbn);

        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            book.setStatus(status);
            log.info("Updated status for item ID {}: new status is {}", isbn, status);
            return true;
        } else {
            log.warn("Attempted to update status for non-existent item ID: {}", isbn);
            return false;
        }
    }

    public List<Book> getAllItems() {
        return new ArrayList<>(books.values());
    }
}