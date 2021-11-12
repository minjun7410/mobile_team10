package com.example.team10;


/* 사용자 계정 정보 */

public class UserAccount {
    private String uid;
    private String email;
    private String password;

    public UserAccount() { }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
