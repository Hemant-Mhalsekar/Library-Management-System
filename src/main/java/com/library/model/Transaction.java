package com.library.model;

import java.time.LocalDate;


public class Transaction {
    // Attributes
     private int transactionId;
     private int bookId;
     private int memberId;
     private LocalDate issueDate;
     private LocalDate returnDate;
     private double fineAmount;

    //Constructor
    public Transaction(int transactionId, int bookId, int memberId, LocalDate issueDate, LocalDate returnDate, double fineAmount){
        this.transactionId = transactionId;
        this.bookId = bookId;
        this.memberId = memberId;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
        this.fineAmount = fineAmount;
    }

    //Getters and Setters

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public double getFineAmount() {
        return fineAmount;
    }

    public void setFineAmount(double fineAmount) {
        this.fineAmount = fineAmount;
    }

    //toString()
    @Override
    public String toString(){
        return "Transaction ID: " + transactionId +
                ", Book ID: " + bookId +
                ", Member ID: " + memberId +
                ", Issued Date: " + issueDate +
                ", Return Date: " + returnDate +
                ", Fine Amount: " + String.format("%.2f", fineAmount) ;
    }
}
