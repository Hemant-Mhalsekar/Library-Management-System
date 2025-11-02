package com.library.dao;

import com.library.model.Member;
import java.time.LocalDate;
import java.util.List;

public class TestMemberDAO {
    public static void main(String[] args) {
        MemberDAO dao = new MemberDAO();

        //Add
        Member m = new Member(1, "Hemant", "Student", LocalDate.now());
        dao.addMember(m);

        //Get All
        List<Member> members = dao.getAllMembers();
        for (Member member : members) {
            System.out.println(member);
        }

        //Get By ID
        Member found = dao.getMemberById(1);
        System.out.println(found != null ? found : "Member not found!");

        //Update
        dao.updateMemberType(1, "Reader");

        //Delete
        dao.deleteMember(1);
    }
}
