package com.cg.feedback.dto;

public class LoginResponse {
    private String userid;
    private String role;
    private String username;
    private String email;
    public LoginResponse() {
    }

    public LoginResponse(String userId, String role, String username, String email) {
        this.userid = userId;
        this.role = role;
        this.username = username;
        this.email = email;
    }


    public String getUserid() {
        return userid;
    }

    public void setUserid(String userId) {
        this.userid = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUser() {
        return username;
    }

    public void setUser(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}