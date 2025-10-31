package com.library.model;

public class Librarian {
    //Attributes
    private int librarianId;
    private String librarianName;
    private String librarianUsername;
    private String librarianPassword;

    //Constructor

    public Librarian(int librarianId, String librarianName, String librarianUsername, String librarianPassword) {
        this.librarianId = librarianId;
        this.librarianName = librarianName;
        this.librarianUsername = librarianUsername;
        this.librarianPassword = librarianPassword;
    }

    //Getters and Setters


    public int getLibrarianId() {
        return librarianId;
    }

    public void setLibrarianId(int librarianId) {
        this.librarianId = librarianId;
    }

    public String getLibrarianName() {
        return librarianName;
    }

    public void setLibrarianName(String librarianName) {
        this.librarianName = librarianName;
    }

    public String getLibrarianUsername() {
        return librarianUsername;
    }

    public void setLibrarianUsername(String librarianUsername) {
        this.librarianUsername = librarianUsername;
    }

    public String getLibrarianPassword() {
        return librarianPassword;
    }

    public void setLibrarianPassword(String librarianPassword) {
        this.librarianPassword = librarianPassword;
    }



    //toString() Method
    @Override
    public String toString() {
        return "Librarian ID: " + librarianId + ", Librarian Name: " + librarianName + ", Librarian Username:" + librarianUsername + ", Librarian Password: " + "*".repeat(librarianPassword.length());
    }
}
