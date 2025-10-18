package org.example;

import org.example.book.Book;
import org.example.searchsStrategy.SearchStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;

public class SearchService {
    private static final Logger log = LoggerFactory.getLogger(SearchService.class);
    private final InventoryService inventoryService;

    public SearchService(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    public List<Book> search(String query, SearchStrategy strategy) {
        Objects.requireNonNull(strategy, "Search strategy cannot be null");
        log.info("Performing search for '{}' using strategy: {}", query, strategy.getClass().getSimpleName());

        List<Book> results = strategy.search(query, inventoryService.getAllItems());

        log.info("Found {} results.", results.size());
        return results;
    }

}
