package com.levancam.demo6.models;

public class  NvDatabase {
    private String DTCodeEmployee;
    private String DTname;
    private String DTPhoneNumber;
    private String DTAdress;
    private String DTcccd;

    public NvDatabase(String DTCodeEmployee, String DTname, String DTPhoneNumber, String DTAdress, String DTcccd) {
        this.DTCodeEmployee = DTCodeEmployee;
        this.DTname = DTname;
        this.DTPhoneNumber = DTPhoneNumber;
        this.DTAdress = DTAdress;
        this.DTcccd = DTcccd;
    }

    public String getDTCodeEmployee() {
        return DTCodeEmployee;
    }

    public void setDTCodeEmployee(String DTCodeEmployee) {
        this.DTCodeEmployee = DTCodeEmployee;
    }

    public String getDTname() {
        return DTname;
    }

    public void setDTname(String DTname) {
        this.DTname = DTname;
    }

    public String getDTPhoneNumber() {
        return DTPhoneNumber;
    }

    public void setDTPhoneNumber(String DTPhoneNumber) {
        this.DTPhoneNumber = DTPhoneNumber;
    }

    public String getDTAdress() {
        return DTAdress;
    }

    public void setDTAdress(String DTAdress) {
        this.DTAdress = DTAdress;
    }

    public String getDTcccd() {
        return DTcccd;
    }

    public void setDTcccd(String DTcccd) {
        this.DTcccd = DTcccd;
    }
}