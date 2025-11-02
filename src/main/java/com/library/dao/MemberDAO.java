package com.library.dao;

import com.library.model.Member;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {

    public void addMember(Member member) {
        String sql = "INSERT INTO members (member_id, member_name, member_type, membership_date) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, member.getMemberId());
            stmt.setString(2, member.getMemberName());
            stmt.setString(3, member.getMemberType());
            stmt.setDate(4, java.sql.Date.valueOf(member.getMembershipDate()));

            int rowInserted = stmt.executeUpdate();

            if (rowInserted > 0) {
                System.out.println("Member added successfully: " + member.getMemberName());
            }
        } catch (SQLException e){
            System.out.println("Error adding Member : " + e.getMessage());
        }
    }

    public List<Member> getAllMembers() {
        List<Member> members = new ArrayList<>();
        String sql = "SELECT * FROM members";

        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement smtm = conn.prepareStatement(sql);
             ResultSet rs = smtm.executeQuery()){

            while (rs.next()) {
                Member member = new Member(
                        rs.getInt("member_id"),
                        rs.getString("member_name"),
                        rs.getString("member_type"),
                        rs.getDate("membership_date").toLocalDate()
                );

                members.add(member);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching members: " + e.getMessage());
        }

        return members;
    }

    public Member getMemberById(int memberId){
        Member member = null;
        String sql = "SELECT * FROM members WHERE member_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, memberId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                member = new Member(
                        rs.getInt("member_id"),
                        rs.getString("member_name"),
                        rs.getString("member_type"),
                        rs.getDate("membership_date").toLocalDate()
                );
            }
        } catch (SQLException e) {
            System.out.println("Error fetching member by ID: " + e.getMessage());
        }

        return member;
    }

    public void deleteMember(int memberId){
        String sql = "DELETE FROM members WHERE member_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, memberId);

            int rowDeleted = stmt.executeUpdate();

            if (rowDeleted > 0) {
                System.out.println("Member with ID " + memberId + " was deleted successfully!");
            } else {
                System.out.println("No member found with ID " + memberId);
            }

        } catch (SQLException e) {
            System.out.println("Error deleting member: " + e.getMessage());
        }
    }

    public void updateMemberType(int memberId, String newType){
        String sql = "UPDATE members SET member_type = ? WHERE member_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, newType);
            stmt.setInt(2, memberId);

            int rowUpdated = stmt.executeUpdate();

            if (rowUpdated > 0) {
                System.out.println("Member type updated successfully for ID: "+ memberId);
            } else {
                System.out.println("No member found with ID "+ memberId);
            }

        } catch (SQLException e) {
            System.out.println("Error updating member type: "+ e.getMessage());
        }
    }
}
