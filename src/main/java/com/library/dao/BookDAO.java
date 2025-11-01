package com.library.dao;

import com.library.model.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    public void addBook(Book book) {
        String sql = "INSERT INTO books (book_id, book_name, book_author, book_availability) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, book.getBookId());
            stmt.setString(2, book.getBookName());
            stmt.setString(3, book.getBookAuthor());
            stmt.setBoolean(4, book.isBookAvailability());

            int rowInserted = stmt.executeUpdate();

            if (rowInserted > 0) {
                System.out.println("Book added successfully: " + book.getBookName());
            }
        } catch (SQLException e) {
            System.out.println("Error adding book: " + e.getMessage());
        }
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String  sql = "SELECT * FROM books";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Book book = new Book(
                        rs.getInt("book_id"),
                        rs.getString("book_name"),
                        rs.getString("book_author"),
                        rs.getBoolean("book_availability")
                );

                books.add(book);
            }

        } catch (SQLException e) {
            System.out.println("Error Fetching books: " + e.getMessage());
        }

        return books;
    }

    public Book getBookById(int bookId) {
        Book book = null;
        String sql = "SELECT * FROM books WHERE book_id = ? ";

        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, bookId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                book = new Book(
                        rs.getInt("book_id"),
                        rs.getString("book_name"),
                        rs.getString("book_author"),
                        rs.getBoolean("book_availability")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error fetching book by ID: " + e.getMessage());
        }

        return book;
    }

    public void deleteBook(int bookId) {
        String sql = "DELETE FROM books WHERE book_id = ?";

        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, bookId);

            int rowsDeleted = stmt.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Book with ID " + bookId + " was deleted successfully!");
            } else {
                System.out.println("No book found with ID " + bookId);
            }

        } catch (SQLException e) {
            System.out.println("Error deleting book: " +e.getMessage());
        }
    }

    public void updateAvailability(int bookId, boolean availability){
        String sql = "UPDATE books SET book_availability = ? WHERE book_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setBoolean(1,availability);
            stmt.setInt(2, bookId);

            int rowUpdated = stmt.executeUpdate();

            if (rowUpdated > 0) {
                System.out.println("Book availability updated successfully for ID: " + bookId);
            } else {
                System.out.println("No book found with ID: " + bookId);
            }
        } catch (SQLException e) {
            System.out.println("Error updating availability: " + e.getMessage());
        }
    }
}
