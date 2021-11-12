package com.example.team10;


/* 사용자 계정 정보 */

public class UserAccount {
    private String uid;
    private String emailId;
    private String password;

    public UserAccount() { }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
