package org.example;

import org.example.book.Book;
import org.example.patron.Patron;
import org.example.searchsStrategy.SearchByAuthorStrategy;
import org.example.searchsStrategy.SearchByTitleStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * The Facade class that provides a simple, unified interface to the complex subsystem
 * of services (Inventory, Lending, Search).
 */
public class Library {
    private static final Logger log = LoggerFactory.getLogger(Library.class);

    private final InventoryService inventoryService;
    private final LendingService lendingService;
    private final SearchService searchService;

    private final Map<String, Patron> patrons = new HashMap<>();

    public Library() {
        // In a real app, these would be injected (e.g., by Spring).
        // Here, we compose the system manually.
        this.inventoryService = new InventoryService();
        this.lendingService = new LendingService(inventoryService);
        this.searchService = new SearchService(inventoryService);
        log.info("Library system initialized.");
    }

    // --- Book Management ---
    public void addBook(String isbn, String title, String author, int pubYear) {
        Book newBook = new Book(isbn, title, author, pubYear);
        inventoryService.addItem(newBook);
    }

    // --- Patron Management ---
    public Patron registerPatron(String name) {
        // Generate a simple unique ID for the new patron.
        String patronId = "P" + UUID.randomUUID().toString().substring(0, 4);
        Patron patron = new Patron(patronId, name);
        patrons.put(patronId, patron);
        log.info("Registered new patron: {}", patron);
        return patron;
    }

    // --- Lending Processes ---
    public void checkoutBook(String patronId, String bookId) {
        Patron patron = patrons.get(patronId);
        if (patron == null) {
            log.error("Checkout failed. Patron with ID {} not found.", patronId);
            return; // Or throw exception
        }
        try {
            lendingService.checkoutItem(patron, bookId);
        } catch (Exception e) {
            log.error("Checkout failed for Patron {} and Book {}: {}", patronId, bookId, e.getMessage());
        }
    }

    public void returnBook(String isbn) {
        try {
            lendingService.returnBook(isbn);
        } catch (Exception e) {
            log.error("Return failed for Book {}: {}", isbn, e.getMessage());
        }
    }

    // --- Search Functionality ---
    public List<Book> findBooksByTitle(String query) {
        return searchService.search(query, new SearchByTitleStrategy());
    }

    public List<Book> findBooksByAuthor(String query) {
        return searchService.search(query, new SearchByAuthorStrategy());
    }
}
