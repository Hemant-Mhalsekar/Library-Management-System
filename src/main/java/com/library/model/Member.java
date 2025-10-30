package com.library.model;
import java.time.LocalDate;

public class Member {
    //Attribute
    private int memberId;
    private String memberName;
    private String memberType;
    private LocalDate membershipDate;

    //Constructor
    public Member(int memberId, String memberName, String memberType, LocalDate membershipDate){
        this.memberId = memberId;
        this.memberName = memberName;
        this.memberType = memberType;
        this.membershipDate = membershipDate;
    }

    //Getters and Setters

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getMemberName(){
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberType(){
        return memberType;
    }

    public void setMemberType(String memberType){
        this.memberType = memberType;
    }

    public LocalDate getMembershipDate(){
        return membershipDate;
    }

    public void setMembershipDate(LocalDate membershipDate){
        this.membershipDate = membershipDate;
    }

    //toString() Method
    @Override
    public String toString(){
        return "Member ID: " + memberId +
                ", Member Name: " + memberName +
                ", Member Type: " + memberType +
                ", Membership Date: " + membershipDate;
    }
}


