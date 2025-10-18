package org.example;

import org.example.book.Book;
import org.example.book.BookStatus;
import org.example.loan.Loan;
import org.example.patron.Patron;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

public class LendingService {

    private static final Logger log = LoggerFactory.getLogger(LendingService.class);
    private final InventoryService inventoryService;
    private final Map<String, Loan> activeLoans = new HashMap<>();

    /**
     * Constructs a LendingService with a required InventoryService.
     *
     * @param inventoryService The service responsible for managing the library's inventory.
     */
    public LendingService(InventoryService inventoryService) {
        this.inventoryService = Objects.requireNonNull(inventoryService, "InventoryService cannot be null");
    }

    /**
     * Checks out an item to a patron.
     *
     * @param patron The patron borrowing the item.
     * @param isbn The ID of the item to be checked out.
     * @throws NoSuchElementException if the item ID does not exist in the inventory.
     * @throws IllegalStateException if the item is not available for checkout.
     */
    public void checkoutItem(Patron patron, String isbn) {
        Objects.requireNonNull(patron, "Patron cannot be null");

        Book book = inventoryService.getBookByIsbn(isbn)
            .orElseThrow(() -> new NoSuchElementException("No item found with ISBN: " + isbn));

        if (book.getStatus() != BookStatus.AVAILABLE) {
            log.warn("Checkout failed for item ID {}: Item is not available. Current status: {}", isbn, book.getStatus());
            throw new IllegalStateException("Book " + isbn + " is not available for checkout.");
        }

        // Perform the checkout process
        inventoryService.updateItemStatus(isbn, BookStatus.BORROWED);
        Loan loan = new Loan(book, patron, LocalDate.now(), LocalDate.now().plusWeeks(2)); // 2-week loan period
        patron.addLoan(loan);

        activeLoans.put(isbn, loan);

        log.info("Item ID {} successfully checked out by Patron ID {}", isbn, patron.getPatronId());
    }

    /**
     * Returns a borrowed item to the library.
     *
     * @param isbn The ID of the item to be returned.
     * @throws NoSuchElementException if the item ID does not exist in the inventory.
     * @throws IllegalStateException if the item is not currently borrowed.
     */
    public void returnBook(String isbn) {
        Loan loan = activeLoans.get(isbn);

        if (loan == null) {
            log.warn("Return failed for item ID {}: No active loan found.", isbn);
            throw new IllegalStateException("Book " + isbn + " is not currently borrowed.");
        }

        loan.setReturnDate(LocalDate.now());

        activeLoans.remove(isbn);

        inventoryService.updateItemStatus(isbn, BookStatus.AVAILABLE);

        log.info("Item ID {} successfully returned by Patron ID {}.", isbn, loan.getPatron().getPatronId());
    }
}