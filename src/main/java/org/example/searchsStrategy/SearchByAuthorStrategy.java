package org.example.searchsStrategy;

import org.example.book.Book;

import java.util.List;

public class SearchByAuthorStrategy implements SearchStrategy {
    @Override
    public List<Book> search(String query, List<Book> books) {
        return books.stream()
            .filter(book -> book.getAuthor().toLowerCase().contains(query.toLowerCase()))
            .toList();
    }
}
