package com.library.dao;

import com.library.model.Librarian;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LibrarianDAO {

    public void addLibrarian(Librarian librarian){
        String sql = "INSERT INTO librarians (librarian_id, librarian_name, librarian_username, librarian_password) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, librarian.getLibrarianId());
            stmt.setString(2, librarian.getLibrarianName());
            stmt.setString(3, librarian.getLibrarianUsername());
            stmt.setString(4, librarian.getLibrarianPassword());

            int rowInserted = stmt.executeUpdate();

            if (rowInserted > 0){
                System.out.println("Librarian added successfully " + librarian.getLibrarianName());
            }
        } catch (SQLException e) {
            System.out.println("Error adding librarian " + e.getMessage());
        }
    }

    public List<Librarian> getAllLibrarians(){
        List<Librarian> librarians = new ArrayList<>();
        String sql = "SELECT * FROM librarians";

        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()){

            while (rs.next()) {
                Librarian librarian = new Librarian(
                        rs.getInt("librarian_id"),
                        rs.getString("librarian_name"),
                        rs.getString("librarian_username"),
                        rs.getString("librarian_password")
                );

                librarians.add(librarian);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching librarians: " +e.getMessage());
        }

        return librarians;
    }

    public Librarian getLibrarianById(int librarianId){
        Librarian librarian = null;
        String sql = "SELECT * FROM librarians WHERE librarian_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, librarianId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                librarian = new Librarian(
                        rs.getInt("librarian_id"),
                        rs.getString("librarian_name"),
                        rs.getString("librarian_username"),
                        rs.getString("librarian_password")
                );
            }
        } catch (SQLException e){
            System.out.println("Error fetching librarian by ID: " + e.getMessage());
        }
        return librarian;
    }

    public void deleteLibrarian(int librarianId){
        String sql = "DELETE FROM librarians WHERE librarian_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, librarianId);

            int rowDeleted = stmt.executeUpdate();

            if (rowDeleted > 0){
                System.out.println("Librarian with ID "+ librarianId +" was deleted successfully!");
            } else {
                System.out.println("No librarian found with ID " + librarianId);
            }

        } catch (SQLException e){
            System.out.println("Error deleting librarian: " +e.getMessage());
        }
    }

    public void updateLibrarianPassword(int librarianId, String newPassword){
        String sql = "UPDATE librarians SET librarian_password = ?  WHERE librarian_id = ?";

        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, newPassword);
            stmt.setInt(2, librarianId);

            int rowUpdated = stmt.executeUpdate();

            if (rowUpdated > 0 ) {
                System.out.println("Password updated successfully for librarian ID: " + librarianId);
            } else {
                System.out.println("No librarian found with ID: " + librarianId);
            }
        } catch (SQLException e) {
            System.out.println("Error updating password: " + e.getMessage());
        }
    }
}
