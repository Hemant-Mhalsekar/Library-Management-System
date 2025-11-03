package com.library.service;

import com.library.dao.MemberDAO;
import com.library.model.Member;
import java.util.List;

public class MemberService {
    private MemberDAO memberDAO = new MemberDAO();

    public void addMember(Member member){
        if (member.getMemberName() == null || member.getMemberName().isEmpty()){
            System.out.println("Member name cannot be empty!");
            return;
        }
        memberDAO.addMember(member);
    }

    public List<Member> getAllMembers() {
        return memberDAO.getAllMembers();
    }

    public Member getMemberById(int id) {
        return memberDAO.getMemberById(id);
    }

    public void deleteMember(int id){
        memberDAO.deleteMember(id);
    }

    public void updateMemberType(int id, String newType){
        memberDAO.updateMemberType(id, newType);
    }
}
