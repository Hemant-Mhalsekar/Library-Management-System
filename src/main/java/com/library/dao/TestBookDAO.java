package com.library.dao;

import com.library.model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class TestBookDAO {
    public static void main(String [] args){
        BookDAO dao = new BookDAO();

        Book foundBook = dao.getBookById(1);

        int idToUpdate = 1; // use an ID that exists in your DB
        boolean newAvailability = false; // try setting to false first

        dao.updateAvailability(idToUpdate, newAvailability);
    }


}
