package com.library.service;

import com.library.dao.LibrarianDAO;
import com.library.model.Librarian;

import java.util.List;

public class LibrarianService {
    private LibrarianDAO librarianDAO = new LibrarianDAO();

    public void addLibrarian(Librarian librarian){
        librarianDAO.addLibrarian(librarian);
    }

    public List<Librarian> getAllLibrarian(){
        return librarianDAO.getAllLibrarians();
    }

    public Librarian getLibrarianById(int id){
        return librarianDAO.getLibrarianById(id);
    }

    public void deleteLibrarian(int id) {
        librarianDAO.deleteLibrarian(id);
    }

    public boolean authenticate(String username, String password) {
        List<Librarian> librarians = librarianDAO.getAllLibrarians();
        for (Librarian l :librarians) {
            if (l.getLibrarianUsername().equals(username) &&
            l.getLibrarianPassword().equals(password)) {
                System.out.println("login successful! Welcome, " + l.getLibrarianName());
                return true;
            }
        }
        System.out.println("Invalid username or password!");
        return false;
    }

    public void updatePassword (int id, String newPassword){
        librarianDAO.updateLibrarianPassword(id, newPassword);
    }
}