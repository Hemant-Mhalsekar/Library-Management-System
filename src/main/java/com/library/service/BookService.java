package com.library.service;

import com.library.dao.BookDAO;
import com.library.model.Book;

import java.util.List;

public class BookService {
    private BookDAO bookDAO = new BookDAO();

    public void addBook(Book book) {
        if (book.getBookName() == null || book.getBookName().isEmpty()) {
            System.out.println("Book name cannot be empty!");
            return;
        }
        bookDAO.addBook(book);
    }

    public List<Book> getAllBooks(){
        return bookDAO.getAllBooks();
    }

    public Book getBookById(int bookId) {
        return bookDAO.getBookById(bookId);
    }

    public void deleteBook(int bookId){
        bookDAO.deleteBook(bookId);
    }

    public void updateAvailability(int bookId, boolean availability){
        bookDAO.updateAvailability(bookId, availability);
    }
}
