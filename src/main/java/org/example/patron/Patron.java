package org.example.patron;

import org.example.loan.Loan;

import java.util.ArrayList;
import java.util.List;

public class Patron {
    private final String patronId;
    private String name;
    private final List<Loan> borrowingHistory;

    public Patron(String patronId, String name) {
        this.patronId = patronId;
        this.name = name;
        this.borrowingHistory = new ArrayList<>();
    }

    // Getters and a setter for name
    public String getPatronId() {
        return patronId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Loan> getBorrowingHistory() {
        return new ArrayList<>(borrowingHistory);
    }

    public List<Loan> getCurrentLoans() {
        return new ArrayList<>(borrowingHistory);
    }

    // Method to add a loan to the history (controlled mutation)
    public void addLoan(Loan loan) {
        this.borrowingHistory.add(loan);
    }

    @Override
    public String toString() {
        return String.format("Patron [ID: %s, Name: '%s']", patronId, name);
    }
}