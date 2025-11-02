package com.library.dao;

import com.library.model.Transaction;
import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {

    public void addTransaction(Transaction transaction) {
        String sql = "INSERT INTO transactions (transaction_id, book_id, member_id, issue_date, return_date, fine_amount) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, transaction.getTransactionId());
            stmt.setInt(2, transaction.getBookId());
            stmt.setInt(3, transaction.getMemberId());
            stmt.setDate(4, java.sql.Date.valueOf(transaction.getIssueDate()));

            if (transaction.getReturnDate() != null) {
                stmt.setDate(5, java.sql.Date.valueOf(transaction.getReturnDate()));
            } else {
                stmt.setNull(5, Types.DATE);
            }

            stmt.setDouble(6, transaction.getFineAmount());

            int rowInserted = stmt.executeUpdate();
            if (rowInserted > 0) {
                System.out.println("Transaction added successfully for Book ID: " + transaction.getBookId());
            }
        } catch (SQLException e) {
            System.out.println("Error adding transaction: " + e.getMessage());
        }
    }

    public List<Transaction> getAllTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transactions";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Transaction transaction = new Transaction(
                        rs.getInt("transaction_id"),
                        rs.getInt("book_id"),
                        rs.getInt("member_id"),
                        rs.getDate("issue_date").toLocalDate(),
                        rs.getDate("return_date") != null ? rs.getDate("return_date").toLocalDate() : null,
                        rs.getDouble("fine_amount")
                );
                transactions.add(transaction);
            }

        } catch (SQLException e) {
            System.out.println("Error fetching transactions: " + e.getMessage());
        }

        return transactions;
    }

    public Transaction getTransactionById(int transactionId) {
        Transaction transaction = null;
        String sql = "SELECT * FROM transactions WHERE transaction_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, transactionId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                transaction = new Transaction(
                        rs.getInt("transaction_id"),
                        rs.getInt("book_id"),
                        rs.getInt("member_id"),
                        rs.getDate("issue_date").toLocalDate(),
                        rs.getDate("return_date") != null ? rs.getDate("return_date").toLocalDate() : null,
                        rs.getDouble("fine_amount")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error fetching transaction by ID: " + e.getMessage());
        }

        return transaction;
    }

    public void updateReturnDateAndFine(int transactionId, LocalDate returnDate) {
        String selectSql = "SELECT issue_date FROM transactions WHERE transaction_id = ?";
        String updateSql = "UPDATE transactions SET return_date = ?, fine_amount = ? WHERE transaction_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement selectStmt = conn.prepareStatement(selectSql);
             PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {

            selectStmt.setInt(1, transactionId);
            ResultSet rs = selectStmt.executeQuery();

            if (rs.next()) {
                LocalDate issueDate = rs.getDate("issue_date").toLocalDate();

                long daysLate = ChronoUnit.DAYS.between(issueDate, returnDate);
                double fine = (daysLate > 7) ? (daysLate - 7) * 5 : 0;

                updateStmt.setDate(1, java.sql.Date.valueOf(returnDate));
                updateStmt.setDouble(2, fine);
                updateStmt.setInt(3, transactionId);

                int rowsUpdated = updateStmt.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Book returned! Fine: ₹" + fine);
                } else {
                    System.out.println("No transaction found with ID: " + transactionId);
                }
            } else {
                System.out.println("Transaction not found for ID: " + transactionId);
            }

        } catch (SQLException e) {
            System.out.println("Error updating return date and fine: " + e.getMessage());
        }
    }

    //Delete transaction (optional)
    public void deleteTransaction(int transactionId) {
        String sql = "DELETE FROM transactions WHERE transaction_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, transactionId);
            int rowDeleted = stmt.executeUpdate();

            if (rowDeleted > 0) {
                System.out.println("Transaction with ID " + transactionId + " deleted successfully!");
            } else {
                System.out.println("No transaction found with ID: " + transactionId);
            }

        } catch (SQLException e) {
            System.out.println("Error deleting transaction: " + e.getMessage());
        }
    }
}
