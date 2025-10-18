# Library Management System

A simple command-line based Library Management System (LMS) built with Java and Maven.

## Description

This project is a demonstration of a simple library management system. It allows for adding books to an inventory, registering patrons, and handling the checkout and return of books. The system also provides functionality to search for books by title, author, or ISBN.

The project is structured to follow common software design patterns, including the Facade and Strategy patterns, to create a modular and maintainable codebase.

## Features

-   **Book Management:** Add new books to the library's inventory.
-   **Patron Management:** Register new patrons with a unique ID.
-   **Lending:** Checkout and return books for registered patrons.
-   **Search:** Search for books by title, author, or ISBN using different strategies.
-   **Logging:** Uses SLF4J for logging important events and errors.

## Class Diagram
![LMS Class Diagram](https://raw.githubusercontent.com/Puru80/lms-assignment/assignment-impl/assets/images/clas-diagram.png)

## Getting Started

### Prerequisites

-   Java 17 or later
-   Apache Maven

### Building the Project

1.  Clone the repository:
    ```sh
    git clone <repository-url>
    ```
2.  Navigate to the project directory:
    ```sh
    cd lms
    ```
3.  Build the project using Maven:
    ```sh
    mvn clean install
    ```

### Running the Application

The `Main` class provides a simple demonstration of the library's functionality. You can run it from your IDE or using the following Maven command:

```sh
mvn exec:java -Dexec.mainClass="org.example.Main"
```

## Usage

The `main` method in the `org.example.Main` class demonstrates the core features of the library. It shows how to:

1.  Add books to the inventory.
2.  Register new patrons.
3.  Search for books.
4.  Check out and return books.

You can modify this class to experiment with the library's functionality.

## Project Structure

The project is organized into the following packages:

-   `org.example`: The root package, containing the main application classes.
    -   `Main`: The entry point of the application.
    -   `Library`: The facade class that provides a simple interface to the system.
    -   `InventoryService`: Manages the library's book inventory.
    -   `LendingService`: Handles book checkouts and returns.
    -   `SearchService`: Provides book search functionality.
-   `org.example.book`: Contains the `Book` model and related enums.
-   `org.example.loan`: Contains the `Loan` model.
-   `org.example.patron`: Contains the `Patron` model.
-   `org.example.searchsStrategy`: Contains the different search strategy implementations.

The project follows a modular design, with each service responsible for a specific part of the library's functionality. The `Library` class acts as a facade, simplifying the interaction with these services.
