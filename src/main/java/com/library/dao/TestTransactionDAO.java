package com.library.dao;

import com.library.model.Transaction;
import java.time.LocalDate;
import java.util.List;

public class TestTransactionDAO {
    public static void main(String[] args) {
        TransactionDAO dao = new TransactionDAO();

        //Add new transaction (Book issued)
        Transaction t1 = new Transaction(1, 101, 201, LocalDate.now(), null, 0.0);
        dao.addTransaction(t1);

        //Get all transactions
        List<Transaction> transactions = dao.getAllTransactions();
        for (Transaction t : transactions) {
            System.out.println(t);
        }

        //Get transaction by ID
        Transaction found = dao.getTransactionById(1);
        System.out.println(found != null ? found : "No transaction found!");

        //Update return date and fine
        dao.updateReturnDateAndFine(1, LocalDate.now(), 0.0);

        //Delete transaction
        dao.deleteTransaction(1);
    }
}
