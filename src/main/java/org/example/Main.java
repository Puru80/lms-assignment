package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import org.example.book.Book;
import org.example.patron.Patron;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();

        System.out.println("--- Populating Library ---");
        library.addBook("978-0321765723", "The C++ Programming Language", "Bjarne Stroustrup", 2013);
        library.addBook("978-0132350884", "Clean Code", "Robert C. Martin", 2008);
        library.addBook("978-0134494166", "Effective Java", "Joshua Bloch", 2018);

        System.out.println("\n--- Registering Patrons ---");
        Patron alice = library.registerPatron("John");
        Patron bob = library.registerPatron("Jane");

        System.out.println("\n--- Searching for a Book ---");
        List<Book> foundBooks = library.findBooksByTitle("code");
        System.out.println("Found books with 'code' in title: " + foundBooks);

        System.out.println("\n--- Performing Checkouts ---");
        library.checkoutBook(alice.getPatronId(), "978-0132350884"); // Alice checks out Clean Code
        library.checkoutBook(bob.getPatronId(), "978-0134494166");   // Bob checks out Effective Java

        // Try to check out an already borrowed book
        System.out.println("\n--- Attempting to check out a borrowed book (should fail) ---");
        library.checkoutBook(alice.getPatronId(), "978-0134494166");

        System.out.println("\n--- Returning a Book ---");
        library.returnBook("978-0132350884"); // Alice returns Clean Code

        System.out.println("\n--- Checking out the returned book ---");
        library.checkoutBook(bob.getPatronId(), "978-0132350884"); // Bob can now check it out
    }
}