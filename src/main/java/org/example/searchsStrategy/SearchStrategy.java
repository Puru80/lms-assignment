package org.example.searchsStrategy;

import org.example.book.Book;

import java.util.List;

public interface SearchStrategy {
    List<Book> search(String query, List<Book> books);
}
