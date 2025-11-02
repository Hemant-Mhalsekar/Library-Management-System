package com.library.dao;

import com.library.model.Librarian;
import java.util.List;

public class TestLibrarianDAO {
    public static void main(String[] args) {
        LibrarianDAO dao = new LibrarianDAO();

        // 1️⃣ Add
        Librarian lib = new Librarian(1, "Admin", "admin123", "password");
        dao.addLibrarian(lib);

        // 2️⃣ Get All
        List<Librarian> librarians = dao.getAllLibrarians();
        for (Librarian l : librarians) {
            System.out.println(l);
        }

        // 3️⃣ Get By ID
        Librarian found = dao.getLibrarianById(1);
        System.out.println(found != null ? found : "No librarian found!");

        // 4️⃣ Update password
        dao.updateLibrarianPassword(1, "newPassword123");

        // 5️⃣ Delete librarian
        dao.deleteLibrarian(1);
    }
}
