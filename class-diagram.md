```
classDiagram
    class Library {
	    -inventoryService: InventoryService
	    -lendingService: LendingService
	    -searchService: SearchService
	    -patrons: Map
	    +addBook(String, String, String, int)
	    +registerPatron(String) : Patron
	    +checkoutBook(String, String)
	    +returnBook(String)
	    +findBooksByTitle(String) : List
	    +findBooksByAuthor(String) : List
    }
    class InventoryService {
	    -books: Map
	    +addItem(Book)
	    +getBookByIsbn(String) : Optional
	    +updateItemStatus(String, BookStatus) : boolean
	    +getAllItems() : List
    }
    class LendingService {
	    -inventoryService: InventoryService
	    -activeLoans: Map
	    +checkoutItem(Patron, String)
	    +returnBook(String)
    }
    class SearchService {
	    -inventoryService: InventoryService
	    +search(String, SearchStrategy) : List
    }
    class Book {
	    -isbn: String
	    -title: String
	    -status: BookStatus
	    -author: String
	    -publicationYear: int
    }
    class BookStatus {
	    AVAILABLE
	    BORROWED
	    RESERVED
	    LOST
    }
    class Patron {
	    -patronId: String
	    -name: String
	    -borrowingHistory: List
	    +addLoan(Loan)
    }
    class Loan {
	    -book: Book
	    -patron: Patron
	    -checkoutDate: LocalDate
	    -dueDate: LocalDate
	    -returnDate: LocalDate
    }
    class SearchStrategy {
	    +search(String, List) : List
    }
    class SearchByAuthorStrategy {
	    +search(String, List) : List
    }
    class SearchByIsbnStrategy {
	    +search(String, List) : List
    }
    class SearchByTitleStrategy {
	    +search(String, List) : List
    }

	<<enumeration>> BookStatus
	<<interface>> SearchStrategy

    Library o-- InventoryService
    Library o-- LendingService
    Library o-- SearchService
    LendingService o-- InventoryService
    SearchService o-- InventoryService
    LendingService ..> Patron
    LendingService ..> Book
    LendingService ..> Loan
    InventoryService ..> Book
    InventoryService ..> BookStatus
    Patron "1" *-- "0..*" Loan
    Loan o-- Book
    Loan o-- Patron
    SearchService ..> SearchStrategy
    SearchStrategy <|.. SearchByAuthorStrategy
    SearchStrategy <|.. SearchByIsbnStrategy
    SearchStrategy <|.. SearchByTitleStrategy
```
