package com.library.service;

import com.library.dao.BookDAO;
import com.library.dao.TransactionDAO;
import com.library.model.Transaction;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class TransactionService {
    private TransactionDAO transactionDAO = new TransactionDAO();
    private BookDAO bookDAO = new BookDAO();

    // Issue book
    public void issueBook(Transaction transaction) {
        bookDAO.updateAvailability(transaction.getBookId(), false);

        transactionDAO.addTransaction(transaction);
        System.out.println("Book issued successfully!");
    }

    // Return book with fine calculation
    public void returnBook(int transactionId, LocalDate returnDate) {
        Transaction transaction = transactionDAO.getTransactionById(transactionId);

        if (transaction != null) {
            long daysLate = ChronoUnit.DAYS.between(transaction.getIssueDate(), returnDate);

            double fine = (daysLate > 7) ? (daysLate - 7) * 5 : 0;

            transactionDAO.updateReturnDateAndFine(transactionId, returnDate, fine);

            bookDAO.updateAvailability(transaction.getBookId(), true);

            System.out.println("Book returned successfully!");
            System.out.println("Days Late: " + (daysLate > 0 ? daysLate : 0));
            System.out.println("Fine Applied: ₹" + fine);
        } else {
            System.out.println("Transaction not found!");
        }
    }

    // View all transactions
    public List<Transaction> getAllTransactions() {
        return transactionDAO.getAllTransactions();
    }

    // View transaction by ID
    public Transaction getTransactionById(int id) {
        return transactionDAO.getTransactionById(id);
    }
}
