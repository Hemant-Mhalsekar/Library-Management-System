package com.library.model;

public class Book {
    // Attributes

    private int bookId;
    private String bookName;
    private String bookAuthor;
    private boolean bookAvailability;

    //Constructors
    public Book(int bookId, String bookName, String bookAuthor, boolean bookAvailability){
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookAvailability = bookAvailability;
    }

    //Getters and Setters
    public int getBookId(){
        return bookId;
    }

    public void setBookId(int bookId){
        this.bookId = bookId;
    }

    public String getBookName(){
        return bookName;
    }

    public void setBookName(String bookName){
        this.bookName = bookName;
    }

    public String getBookAuthor(){
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor){
        this.bookAuthor = bookAuthor;
    }

    public boolean isBookAvailability(){
        return bookAvailability;
    }

    public void setBookAvailable(boolean bookAvailability){
        this.bookAvailability = bookAvailability;
    }

    //toString() Method
    @Override //Tells the compiler you’re replacing a parent method safely
    public String toString(){ //Defines how your object looks when printed as text
        return "Book ID: " + bookId +
                ", Name: " + bookName +
                ", Author: " + bookAuthor +
                ", Availability: " + (bookAvailability ? "Yes" : "No");
    }
}
